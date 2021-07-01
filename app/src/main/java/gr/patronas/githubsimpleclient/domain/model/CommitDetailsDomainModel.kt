package gr.patronas.githubsimpleclient.domain.model

data class CommitDetailsDomainModel(
    val sha: String,
    val message: String,
    val author: String,
    val date: String
)