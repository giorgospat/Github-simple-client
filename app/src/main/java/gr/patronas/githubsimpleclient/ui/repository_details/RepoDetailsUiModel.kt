package gr.patronas.githubsimpleclient.ui.repository_details

import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel

data class RepoDetailsUiModel(
    var showLoading: Boolean = false,
    var errorMessage: String? = null,
    var listData: List<CommitDetailsDomainModel>? = null
)