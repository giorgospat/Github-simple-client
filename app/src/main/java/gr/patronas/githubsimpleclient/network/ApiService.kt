package gr.patronas.githubsimpleclient.network

import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/repos/{owner}/{repo}")
    suspend fun fetchRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<FetchRepoApiResponse>

    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getRepoCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("per_page") limit: Int
    ): Response<BaseApiResponseList<FetchRepoCommitsApiResponse>>

}