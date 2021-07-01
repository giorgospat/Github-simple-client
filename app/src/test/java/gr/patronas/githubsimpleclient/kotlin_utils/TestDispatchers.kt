package gr.patronas.githubsimpleclient.kotlin_utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestDispatchers {

    fun getDispatchers(): DispatcherProviderImpl {
        return DispatcherProviderImpl(
            mainThread = Dispatchers.Unconfined,
            coroutineScope = CustomCoroutineScope(),
            backgroundThread = Dispatchers.Unconfined
        )
    }

    class CustomCoroutineScope : CoroutineScope {

        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Unconfined
    }

}