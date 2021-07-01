package gr.patronas.githubsimpleclient.domain.mapper

import gr.patronas.githubsimpleclient.common_android.DateFormats.DATE_FORMAT_DAY_MONTH_YEAR_HOUR_MINUTES_SECONDS
import gr.patronas.githubsimpleclient.common_android.DateFormats.SERVER_ZULU_SECONDS_WITH_Z_FORMAT
import gr.patronas.githubsimpleclient.common_android.extensions.formatDate
import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel
import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse
import javax.inject.Inject

class RepoDetailsMapper @Inject constructor() {

    fun mapCommitsToDomain(apiResponse: BaseApiResponseList<FetchRepoCommitsApiResponse>): List<CommitDetailsDomainModel> {
        val domainResponse: ArrayList<CommitDetailsDomainModel> = arrayListOf()
        apiResponse.list.forEach { item ->
            domainResponse.add(
                CommitDetailsDomainModel(
                    sha = item.sha ?: "",
                    message = item.commit?.message ?: "",
                    author = item.commit?.author?.name ?: "",
                    date = item.commit?.author?.date.formatDate(
                        fromDateFormat = SERVER_ZULU_SECONDS_WITH_Z_FORMAT,
                        toDateFormat = DATE_FORMAT_DAY_MONTH_YEAR_HOUR_MINUTES_SECONDS
                    )
                )
            )
        }
        domainResponse.sortByDescending {
            it.date
        }
        return domainResponse
    }
}