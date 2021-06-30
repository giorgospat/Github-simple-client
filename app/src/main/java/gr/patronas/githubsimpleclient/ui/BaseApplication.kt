package gr.patronas.githubsimpleclient.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import gr.patronas.githubsimpleclient.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}