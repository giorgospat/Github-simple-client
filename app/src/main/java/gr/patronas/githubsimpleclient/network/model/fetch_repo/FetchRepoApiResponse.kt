package gr.patronas.githubsimpleclient.network.model.fetch_repo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gr.patronas.githubsimpleclient.network.model.BaseApiResponse

@JsonClass(generateAdapter = true)
data class FetchRepoApiResponse(
    @Json(name = "id")
    val repositoryId: String
) : BaseApiResponse()