package gr.patronas.githubsimpleclient.ui.repository_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.patronas.githubsimpleclient.databinding.LayoutCommitListitemBinding
import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel


class CommitsRecycleAdapter(
    var commits: List<CommitDetailsDomainModel>
) :
    RecyclerView.Adapter<CommitsViewHolder>() {

    override fun getItemCount() = commits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {
        return CommitsViewHolder(
            binding =
            LayoutCommitListitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {
        val commit = commits[position]

        with(holder.binding) {
            with(commit) {
                txtShaValue.text = sha
                txtMessageValue.text = message
                txtAuthorValue.text = author
                txtDateValue.text = date
            }
        }

    }


}


class CommitsViewHolder(val binding: LayoutCommitListitemBinding) :
    RecyclerView.ViewHolder(binding.root)
