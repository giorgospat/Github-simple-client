package gr.patronas.githubsimpleclient.ui.repository_details

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
class RepoDetailsViewModel @Inject constructor(
    private val fetchRepoUseCase: FetchGithubRepoUseCase,
    private val androidResources: AndroidResources
) : ViewModel(), CoroutineScope {

    private val _uiModel = MutableLiveData<RepoDetailsUiModel>()
    val uiModel: LiveData<RepoDetailsUiModel> = _uiModel

    fun fetchRepoCommits(owner: String, repoName: String, limit: Int) {
        _uiModel.value = RepoDetailsUiModel(
            showLoading = true
        )
        launch {
            val result = fetchRepoUseCase.fetchRepoCommits(
                owner = owner,
                repoName = repoName,
                limit = limit
            )
            launch(Dispatchers.Main) {
                when (result) {
                    is GenericResult.Success -> {
                        _uiModel.value = RepoDetailsUiModel(
                            showLoading = false,
                            listData = result.data
                        )
                    }
                    else -> {
                        _uiModel.value = RepoDetailsUiModel(
                            showLoading = false,
                            errorMessage = androidResources.getString(R.string.error_fetching_commits)
                        )
                    }
                }
            }
        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

}