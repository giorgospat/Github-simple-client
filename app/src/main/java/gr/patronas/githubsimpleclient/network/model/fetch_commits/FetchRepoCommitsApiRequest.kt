package gr.patronas.githubsimpleclient.network.model.fetch_commits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FetchRepoCommitsApiRequest(
    @Json(name = "owner")
    val owner: String,
    @Json(name = "repo")
    val repo: String,
    @Json(name = "limit")
    val limit: Int,
)