package gr.patronas.githubsimpleclient.di.module

import android.app.Application
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.patronas.githubsimpleclient.BuildConfig
import gr.patronas.githubsimpleclient.network.ApiService
import gr.patronas.githubsimpleclient.network.CacheInterceptor
import gr.patronas.githubsimpleclient.network.NetworkConstants.BASE_API_URL
import gr.patronas.githubsimpleclient.network.adapter.FetchRepoCommitsAdapter
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(
        fetchRepoCommitsAdapter: FetchRepoCommitsAdapter
    ): Moshi {
        return Moshi.Builder()
            .add(fetchRepoCommitsAdapter)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong()
        return Cache(File(application.cacheDir, "repo-cache"), cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, cacheInterceptor: CacheInterceptor) =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        } else OkHttpClient
            .Builder()
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

    @Provides
    //  @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_API_URL)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

}