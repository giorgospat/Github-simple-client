package gr.patronas.githubsimpleclient.network.model.fetch_repo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FetchRepoApiRequest(
    @Json(name = "owner")
    val owner: String,
    @Json(name = "repo")
    val repo: String
)