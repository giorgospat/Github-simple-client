package gr.patronas.githubsimpleclient.network.model.fetch_commits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gr.patronas.githubsimpleclient.network.model.BaseApiResponse

@JsonClass(generateAdapter = true)
data class FetchRepoCommitsApiResponse(
    @Json(name = "sha")
    val sha: String?,
    @Json(name = "commit")
    val commit: Commit?
) : BaseApiResponse()

@JsonClass(generateAdapter = true)
data class Commit(
    @Json(name = "author")
    val author: Author?,
    @Json(name = "message")
    val message: String?
)

@JsonClass(generateAdapter = true)
data class Author(
    @Json(name = "name")
    val name: String?,
    @Json(name = "date")
    val date: String?
)
