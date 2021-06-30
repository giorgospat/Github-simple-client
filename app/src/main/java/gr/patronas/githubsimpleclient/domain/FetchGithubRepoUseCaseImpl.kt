package gr.patronas.githubsimpleclient.domain

import gr.patronas.githubsimpleclient.data.repositories.FetchGithubRepoRepositoryImpl
import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse

class FetchGithubRepoUseCaseImpl(private val repository: FetchGithubRepoRepositoryImpl) : FetchGithubRepoUseCase {

    override suspend fun fetchRepo(
        owner: String,
        repoName: String
    ): GenericResult<FetchRepoApiResponse> {
        return repository.performSalesTransaction(
            FetchRepoApiRequest(
                owner = owner,
                repo = repoName
            )
        )
    }

}