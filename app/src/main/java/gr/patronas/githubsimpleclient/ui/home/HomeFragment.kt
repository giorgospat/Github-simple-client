package gr.patronas.githubsimpleclient.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gr.patronas.githubsimpleclient.common_android.extensions.displaySnackbar
import gr.patronas.githubsimpleclient.common_android.extensions.hideKeyboard
import gr.patronas.githubsimpleclient.databinding.FragmentHomeBinding


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchAndObserveUiModel()
    }

    private fun fetchAndObserveUiModel() {
        viewModel.uiModel.observe(viewLifecycleOwner, {
            updateUi(uiModel = it)
        })
    }

    private fun initViews() {
        binding.btnFetchRepo.setOnClickListener {
            view?.let { activity?.hideKeyboard(it) }
            viewModel.fetchGithubRepository(
                owner = binding.edittextOwner.text.toString(),
                repoName = binding.edittextRepoName.text.toString()
            )
        }
    }

    private fun updateUi(uiModel: HomeUiModel) {
        if (isAdded) {
            with(binding) {
                with(uiModel) {
                    if (showLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.GONE
                    }
                    if (repositoryIsValid) {
                        repoDetailsArgument?.let {
                            val action =
                                HomeFragmentDirections.actionFirstFragmentToSecondFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                    errorMessage?.let {
                        constraintHomeParent.displaySnackbar(it)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearUiModel()
        _binding = null
    }

}