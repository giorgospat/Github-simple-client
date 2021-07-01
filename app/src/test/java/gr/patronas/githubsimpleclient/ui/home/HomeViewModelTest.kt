package gr.patronas.githubsimpleclient.ui.home

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.any
import gr.patronas.githubsimpleclient.R
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResources
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResourcesImpl
import gr.patronas.githubsimpleclient.domain.FetchGithubRepoUseCase
import gr.patronas.githubsimpleclient.kotlin_utils.DispatcherProvider
import gr.patronas.githubsimpleclient.kotlin_utils.TestDispatchers
import gr.patronas.githubsimpleclient.network.model.GenericResult
import gr.patronas.githubsimpleclient.network.model.fetch_repo.FetchRepoApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var androidResources: AndroidResources
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val dispatcherProvider: DispatcherProvider = TestDispatchers().getDispatchers()
    private val dummyRepoId = "123456789"

    @Mock
    private lateinit var fetchRepoUseCase: FetchGithubRepoUseCase

    private val successResponse = GenericResult.Success(
        FetchRepoApiResponse(
            repositoryId = dummyRepoId
        )
    )

    private val errorResponse = GenericResult.Error<FetchRepoApiResponse>()


    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        androidResources = AndroidResourcesImpl(context = context)

        viewModel = HomeViewModel(
            fetchRepoUseCase = fetchRepoUseCase,
            androidResources = androidResources,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Test
    fun `given fragment destroyed verify uiModel livedata gets the default state`() {
        viewModel.clearUiModel()
        assert(viewModel.uiModel.value == HomeUiModel())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given get repository button pressed with valid repository verify ui will return success state`() {
        val dummyOwner = "dummyOwner"
        val dummyRepoName = "dummyRepoName"

        runBlocking {
            Mockito.`when`(fetchRepoUseCase.fetchRepoDetails(any(), any())).thenReturn(
                successResponse
            )
            viewModel.fetchGithubRepository(owner = dummyOwner, repoName = dummyRepoName)

            assert(
                viewModel.uiModel.value ==
                        HomeUiModel(
                            showLoading = false,
                            repositoryIsValid = true,
                            repoDetailsArgument = RepoDetailsArgument(
                                repoOwner = dummyOwner,
                                repoName = dummyRepoName,
                                repoId = dummyRepoId
                            )
                        )
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given get repository button pressed with INVALID repository verify ui will return error state`() {
        val dummyOwner = "dummyOwner"
        val dummyRepoName = "dummyRepoName"

        runBlocking {
            Mockito.`when`(fetchRepoUseCase.fetchRepoDetails(any(), any())).thenReturn(
                errorResponse
            )
            viewModel.fetchGithubRepository(owner = dummyOwner, repoName = dummyRepoName)

            assert(
                viewModel.uiModel.value ==
                        HomeUiModel(
                            showLoading = false,
                            repositoryIsValid = false,
                            errorMessage = androidResources.getString(R.string.error_fetching_repository)
                        )
            )
        }
    }


}