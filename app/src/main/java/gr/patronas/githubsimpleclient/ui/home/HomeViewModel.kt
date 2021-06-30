package gr.patronas.githubsimpleclient.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.patronas.githubsimpleclient.domain.FetchGithubRepoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRepoUseCase: FetchGithubRepoUseCase
) : ViewModel(), CoroutineScope {

    fun fetchGithubRepository(owner: String, repoName: String) {
        launch {
            fetchRepoUseCase.fetchRepo(
                owner = owner,
                repo = repoName
            )
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

}