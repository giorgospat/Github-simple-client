package gr.patronas.githubsimpleclient.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResources
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResourcesImpl

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideAndroidResources(@ApplicationContext context: Context): AndroidResources {
        return AndroidResourcesImpl(context = context)
    }
}