package gr.patronas.githubsimpleclient.ui.repository_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gr.patronas.githubsimpleclient.R
import gr.patronas.githubsimpleclient.databinding.FragmentRepoDetailsBinding
import gr.patronas.githubsimpleclient.network.NetworkConstants
import gr.patronas.githubsimpleclient.ui.repository_details.adapter.CommitsRecycleAdapter


@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

    private var _binding: FragmentRepoDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: RepoDetailsViewModel by viewModels()
    private val args: RepoDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAndObserveData()
    }

    private fun fetchAndObserveData() {
        args.repoDetails?.let { args ->

            binding.txtRepositoryId.text = getString(R.string.repository_id_label, args.repoId)

            viewModel.fetchRepoCommits(
                owner = args.repoOwner,
                repoName = args.repoName,
                limit = NetworkConstants.COMMITS_LIMIT
            )
            viewModel.uiModel.observe(viewLifecycleOwner, {
                updateUi(uiModel = it)
            })
        }
    }

    private fun updateUi(uiModel: RepoDetailsUiModel) {
        with(binding) {
            with(uiModel) {
                if (showLoading) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }

                listData?.let {
                    val recyclerAdapter = CommitsRecycleAdapter(
                        commits = it
                    )
                    val layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    recycleCommitsList.layoutManager = layoutManager
                    recycleCommitsList.adapter = recyclerAdapter
                    recycleCommitsList.itemAnimator = null
                    recycleCommitsList.isNestedScrollingEnabled = false
                    val divider = DividerItemDecoration(
                        recycleCommitsList.context,
                        layoutManager.orientation
                    )
                    recycleCommitsList.addItemDecoration(divider)

                    recyclerAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}