package gr.patronas.githubsimpleclient.data.repositories

import gr.patronas.githubsimpleclient.network.ApiService
import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse

open class FetchGithubRepoRepositoryImpl(private val apiService: ApiService) : BaseRepository() {

    suspend fun fetchRepository(apiRequest: FetchRepoApiRequest): GenericResult<FetchRepoApiResponse> {
        return try {
            execute(
                apiService.fetchRepository(
                    owner = apiRequest.owner,
                    repo = apiRequest.repo
                )
            )
        } catch (e: Exception) {
            return GenericResult.Error()
        }
    }

    suspend fun getRepoCommits(apiRequest: FetchRepoCommitsApiRequest): GenericResult<BaseApiResponseList<FetchRepoCommitsApiResponse>> {
        return try {
            execute(
                apiService.getRepoCommits(
                    owner = apiRequest.owner,
                    repo = apiRequest.repo,
                    limit = apiRequest.limit
                )
            )
        } catch (e: Exception) {
            return GenericResult.Error()
        }
    }


}