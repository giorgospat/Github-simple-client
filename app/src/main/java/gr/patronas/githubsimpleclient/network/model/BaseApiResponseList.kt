package gr.patronas.githubsimpleclient.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BaseApiResponseList<T>(var list: List<T>) : BaseApiResponse()