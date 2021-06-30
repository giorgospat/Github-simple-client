package gr.patronas.githubsimpleclient.network

import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/repos/{owner}/{repo}")
    suspend fun fetchRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<FetchRepoApiResponse>

}