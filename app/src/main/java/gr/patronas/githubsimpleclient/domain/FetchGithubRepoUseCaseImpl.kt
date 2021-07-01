package gr.patronas.githubsimpleclient.domain

import gr.patronas.githubsimpleclient.data.repositories.FetchGithubRepoRepositoryImpl
import gr.patronas.githubsimpleclient.domain.mapper.RepoDetailsMapper
import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel
import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiRequest
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse

class FetchGithubRepoUseCaseImpl(
    private val repository: FetchGithubRepoRepositoryImpl,
    private val repoCommitsMapper: RepoDetailsMapper
) :
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
    ): GenericResult<List<CommitDetailsDomainModel>> {
        return when (val response = repository.getRepoCommits(
            FetchRepoCommitsApiRequest(
                owner = owner,
                repo = repoName,
                limit = limit
            )
        )) {
            is GenericResult.Success -> {
                GenericResult.Success(repoCommitsMapper.mapCommitsToDomain(response.data))
            }
            is GenericResult.Error -> {
                GenericResult.Error()
            }
        }
    }

}