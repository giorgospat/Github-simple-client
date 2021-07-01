package gr.patronas.githubsimpleclient.common_android.resources

interface AndroidResources {
    fun getString(id: Int): String
    fun getString(resId: Int, vararg formatArgs: Any?): String
}