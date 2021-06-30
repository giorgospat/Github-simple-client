package gr.patronas.githubsimpleclient.domain

import gr.patronas.githubsimpleclient.data.repositories.FetchGithubRepoRepositoryImpl
import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse

class FetchGithubRepoUseCaseImpl(private val repository: FetchGithubRepoRepositoryImpl) :
    FetchGithubRepoUseCase {

    override suspend fun fetchRepoDetails(
        owner: String,
        repoName: String
    ): GenericResult<FetchRepoApiResponse> {
        return repository.fetchRepository(
            FetchRepoApiRequest(
                owner = owner,
                repo = repoName
            )
        )
    }

    override suspend fun fetchRepoCommits(
        owner: String,
        repoName: String,
        limit: Int
    ): GenericResult<BaseApiResponseList<FetchRepoCommitsApiResponse>> {
        return repository.getRepoCommits(
            FetchRepoCommitsApiRequest(
                owner = owner,
                repo = repoName,
                limit = limit
            )
        )
    }

}