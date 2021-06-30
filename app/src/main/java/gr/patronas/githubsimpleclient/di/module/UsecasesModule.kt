package gr.patronas.githubsimpleclient.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gr.patronas.githubsimpleclient.data.repositories.FetchGithubRepoRepositoryImpl
import gr.patronas.githubsimpleclient.domain.FetchGithubRepoUseCase
import gr.patronas.githubsimpleclient.domain.FetchGithubRepoUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class UsecasesModule {

    @Provides
    fun provideFetchRepoUseCase(repository: FetchGithubRepoRepositoryImpl): FetchGithubRepoUseCase {
        return FetchGithubRepoUseCaseImpl(repository)
    }
}