package gr.patronas.githubsimpleclient.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.patronas.githubsimpleclient.R
import gr.patronas.githubsimpleclient.common_android.resources.AndroidResources
import gr.patronas.githubsimpleclient.domain.FetchGithubRepoUseCase
import gr.patronas.githubsimpleclient.network.model.GenericResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRepoUseCase: FetchGithubRepoUseCase,
    private val androidResources: AndroidResources
) : ViewModel(), CoroutineScope {

    private val _uiModel = MutableLiveData<HomeUiModel>()
    val uiModel: LiveData<HomeUiModel> = _uiModel

    fun fetchGithubRepository(owner: String, repoName: String) {
        _uiModel.value = HomeUiModel(
            showLoading = true
        )
        launch {
            val result = fetchRepoUseCase.fetchRepoDetails(
                owner = owner,
                repoName = repoName
            )
            launch(Dispatchers.Main) {
                when (result) {
                    is GenericResult.Success -> {
                        _uiModel.value = HomeUiModel(
                            showLoading = false,
                            repositoryIsValid = true,
                            repoDetailsArgument = RepoDetailsArgument(
                                repoOwner = owner,
                                repoName = repoName,
                                repoId = result.data.repositoryId
                            )
                        )
                    }
                    else -> {
                        _uiModel.value = HomeUiModel(
                            showLoading = false,
                            repositoryIsValid = false,
                            errorMessage = androidResources.getString(R.string.error_fetching_repository)
                        )
                    }
                }
            }
        }
    }

    fun clearUiModel() {
        _uiModel.value = HomeUiModel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

}