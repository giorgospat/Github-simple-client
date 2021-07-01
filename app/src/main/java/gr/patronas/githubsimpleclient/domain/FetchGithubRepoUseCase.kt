package gr.patronas.githubsimpleclient.domain

import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel
import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse

interface FetchGithubRepoUseCase {
    suspend fun fetchRepoDetails(owner: String, repoName: String): GenericResult<FetchRepoApiResponse>
    suspend fun fetchRepoCommits(
        owner: String,
        repoName: String,
        limit: Int
    ): GenericResult<List<CommitDetailsDomainModel>>
}