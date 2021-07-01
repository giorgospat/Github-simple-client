package gr.patronas.githubsimpleclient.kotlin_utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface DispatcherProvider {
    fun getMainThread(): CoroutineDispatcher
    fun getBackgroundThread(): CoroutineDispatcher
    fun getCoroutineScope(): CoroutineScope
}

class DispatcherProviderImpl(
    private val mainThread: CoroutineDispatcher,
    private val backgroundThread: CoroutineDispatcher,
    private val coroutineScope: CoroutineScope
) : DispatcherProvider {
    override fun getMainThread(): CoroutineDispatcher = mainThread

    override fun getBackgroundThread(): CoroutineDispatcher = backgroundThread

    override fun getCoroutineScope(): CoroutineScope = coroutineScope
}