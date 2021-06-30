package gr.patronas.githubsimpleclient.data.repositories

import gr.patronas.githubsimpleclient.network.model.BaseApiResponse
import gr.patronas.githubsimpleclient.network.model.GenericResult
import retrofit2.Response

abstract class BaseRepository {

    fun <T : BaseApiResponse> execute(response: Response<T>): GenericResult<T> {

        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return GenericResult.Success(it)
            } ?: return GenericResult.Error()
        } else {
            return GenericResult.Error()
        }

    }

}