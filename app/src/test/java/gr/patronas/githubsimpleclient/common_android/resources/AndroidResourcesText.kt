package gr.patronas.githubsimpleclient.common_android.resources

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import gr.patronas.githubsimpleclient.R
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class AndroidResourcesText {

    private lateinit var androidResources: AndroidResources
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun init() {
        androidResources = AndroidResourcesImpl(context = context)
    }

    @Test
    fun `given a string id, verify usecase returns the correct string`() {
        assertTrue(androidResources.getString(R.string.app_name) == "GithubSimpleClient")
    }


    @Test
    fun `given a string id with placeholders, verify usecase returns the correct string`() {
        val dummyPlaceholder = "testID"
        assertTrue(
            androidResources.getString(
                R.string.repository_id_label,
                dummyPlaceholder
            ) == "Repository id: $dummyPlaceholder"
        )
    }


}