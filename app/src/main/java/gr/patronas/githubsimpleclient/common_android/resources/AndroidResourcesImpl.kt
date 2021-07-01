package gr.patronas.githubsimpleclient.common_android.resources

import android.content.Context

class AndroidResourcesImpl(private val context: Context) : AndroidResources {
    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getString(resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}