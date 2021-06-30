package gr.patronas.githubsimpleclient.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gr.patronas.githubsimpleclient.data.repositories.FetchGithubRepoRepositoryImpl
import gr.patronas.githubsimpleclient.network.ApiService

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideFetchRepoRepository(apiService: ApiService): FetchGithubRepoRepositoryImpl {
        return FetchGithubRepoRepositoryImpl(apiService = apiService)
    }
}