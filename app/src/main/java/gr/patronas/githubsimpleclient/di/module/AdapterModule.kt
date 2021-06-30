package gr.patronas.githubsimpleclient.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.patronas.githubsimpleclient.network.adapter.FetchRepoCommitsAdapter

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    fun provideFetchRepoCommitsAdapter(): FetchRepoCommitsAdapter {
        return FetchRepoCommitsAdapter()
    }
}