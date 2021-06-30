package gr.patronas.githubsimpleclient.domain

import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse

interface FetchGithubRepoUseCase {
    suspend fun fetchRepo(owner: String, repo: String): GenericResult<FetchRepoApiResponse>
}