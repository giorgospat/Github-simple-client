package gr.patronas.githubsimpleclient.data.repositories

import android.util.Log
import gr.patronas.githubsimpleclient.network.model.BaseApiResponse
import gr.patronas.githubsimpleclient.network.model.GenericResult
import retrofit2.Response
import timber.log.Timber

abstract class BaseRepository {

    fun <T : BaseApiResponse> execute(response: Response<T>): GenericResult<T> {


        if (response.isSuccessful) {
            response.raw().cacheResponse?.let {
                Timber.log(Log.INFO, "NetworkResponse: response from cache")
            }
            response.raw().networkResponse?.let {
                Timber.log(Log.INFO, "NetworkResponse: response from Network")
            }

            val body = response.body()
            body?.let {
                return GenericResult.Success(it)
            } ?: return GenericResult.Error()
        } else {
            Timber.log(Log.ERROR, "NetworkResponse: response error")
            return GenericResult.Error()
        }

    }

}