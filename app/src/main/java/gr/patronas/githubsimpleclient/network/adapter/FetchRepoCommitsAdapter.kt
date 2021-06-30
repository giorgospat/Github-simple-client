package gr.patronas.githubsimpleclient.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse


class FetchRepoCommitsAdapter {

    @FromJson
    fun fromJson(
        reader: JsonReader,
        jsonAdapter: JsonAdapter<FetchRepoCommitsApiResponse>
    ): BaseApiResponseList<FetchRepoCommitsApiResponse> {

        val list = ArrayList<FetchRepoCommitsApiResponse>()
        if (reader.hasNext()) {
            val token: JsonReader.Token = reader.peek()
            if (token == JsonReader.Token.BEGIN_ARRAY) {
                reader.beginArray()
                while (reader.hasNext()) {
                    val response: FetchRepoCommitsApiResponse? =
                        jsonAdapter.fromJsonValue(reader.readJsonValue())
                    response?.let {
                        list.add(response)
                    }
                }
                reader.endArray()
            }
        }

        return BaseApiResponseList(list)
    }
}