package gr.patronas.githubsimpleclient.ui.home

data class HomeUiModel(
    var showLoading: Boolean = false,
    var repositoryIsValid: Boolean = false,
    var errorMessage: String? = null,
    var repoDetailsArgument: RepoDetailsArgument? = null
)