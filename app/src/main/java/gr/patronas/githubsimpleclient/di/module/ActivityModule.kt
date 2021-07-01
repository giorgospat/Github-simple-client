package gr.patronas.githubsimpleclient.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import gr.patronas.githubsimpleclient.kotlin_utils.DispatcherProvider
import gr.patronas.githubsimpleclient.kotlin_utils.DispatcherProviderImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ActivityRetainedComponent::class)
class ActivityModule {

    @Provides
    fun providesCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Provides
    fun provideDispatcherProvider(coroutineScope: CoroutineScope): DispatcherProvider {
        return DispatcherProviderImpl(
            mainThread = Dispatchers.Main,
            backgroundThread = Dispatchers.IO,
            coroutineScope = coroutineScope
        )
    }

}