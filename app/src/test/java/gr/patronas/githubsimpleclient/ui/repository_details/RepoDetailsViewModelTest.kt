package gr.patronas.githubsimpleclient.ui.repository_details

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.any
import gr.patronas.githubsimpleclient.R
import gr.patronas.githubsimpleclient.TestConstants.dummyAuthorName
import gr.patronas.githubsimpleclient.TestConstants.dummyCommitLimit
import gr.patronas.githubsimpleclient.TestConstants.dummyFormatedDate
import gr.patronas.githubsimpleclient.TestConstants.dummyMessage
import gr.patronas.githubsimpleclient.TestConstants.dummySha
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResources
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResourcesImpl
import gr.patronas.githubsimpleclient.domain.FetchGithubRepoUseCase
import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel
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
class RepoDetailsViewModelTest {

    private lateinit var viewModel: RepoDetailsViewModel
    private lateinit var androidResources: AndroidResources
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val dispatcherProvider: DispatcherProvider = TestDispatchers().getDispatchers()

    @Mock
    private lateinit var fetchRepoUseCase: FetchGithubRepoUseCase

    private val successResponse = GenericResult.Success(
        listOf(
            CommitDetailsDomainModel(
                sha = dummySha,
                message = dummyMessage,
                author = dummyAuthorName,
                date = dummyFormatedDate
            )
        )
    )

    private val errorResponse = GenericResult.Error<FetchRepoApiResponse>()


    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        androidResources = AndroidResourcesImpl(context = context)

        viewModel = RepoDetailsViewModel(
            fetchRepoUseCase = fetchRepoUseCase,
            androidResources = androidResources,
            dispatcherProvider = dispatcherProvider
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given repository commits requested with VALID response, verify ui will create commits list`() {
        val dummyOwner = "dummyOwner"
        val dummyRepoName = "dummyRepoName"

        runBlocking {
            Mockito.`when`(fetchRepoUseCase.fetchRepoCommits(any(), any(), any())).thenReturn(
                successResponse
            )
            viewModel.fetchRepoCommits(
                owner = dummyOwner,
                repoName = dummyRepoName,
                limit = dummyCommitLimit
            )

            assert(
                viewModel.uiModel.value ==
                        RepoDetailsUiModel(
                            showLoading = false,
                            listData = listOf(
                                CommitDetailsDomainModel(
                                    sha = dummySha,
                                    message = dummyMessage,
                                    author = dummyAuthorName,
                                    date = dummyFormatedDate
                                )
                            )
                        )
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given repository commits requested with INVALID response, verify ui will show error message`() {
        val dummyOwner = "dummyOwner"
        val dummyRepoName = "dummyRepoName"

        runBlocking {
            Mockito.`when`(fetchRepoUseCase.fetchRepoDetails(any(), any())).thenReturn(
                errorResponse
            )

            viewModel.fetchRepoCommits(
                owner = dummyOwner,
                repoName = dummyRepoName,
                limit = dummyCommitLimit
            )

            assert(
                viewModel.uiModel.value ==
                        RepoDetailsUiModel(
                            showLoading = false,
                            errorMessage = androidResources.getString(R.string.error_fetching_commits)
                        )
            )
        }
    }


}