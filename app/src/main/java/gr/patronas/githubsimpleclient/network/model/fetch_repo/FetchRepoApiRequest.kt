package gr.patronas.githubsimpleclient.network.model.fetch_repo

import com.google.gson.annotations.SerializedName

data class FetchRepoApiRequest(
    @SerializedName("owner")
    val owner: String,
    @SerializedName("repo")
    val repo: String
)