package gr.patronas.githubsimpleclient.ui.home

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class RepoDetailsArgument(
    val repoOwner: String,
    val repoName: String,
    val repoId: String
) : Serializable