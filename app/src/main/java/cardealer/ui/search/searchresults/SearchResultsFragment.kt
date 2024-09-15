package com.example.autohub.ui.search.searchresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.autohub.R
import com.example.autohub.databinding.FragmentSearchResultsBinding
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.adapters.CarAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchResultsFragment : Fragment(), ScreenSwitchable {

    private var _binding: FragmentSearchResultsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _carAdapter: CarAdapter? = null
    private val carAdapter get() = requireNotNull(_carAdapter)

    private val args: SearchResultsFragmentArgs by navArgs()

    private val searchResultsViewModel by viewModel<SearchResultsViewModel> { parametersOf(this) }

    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _carAdapter = CarAdapter { item ->
            val args =
                SearchResultsFragmentDirections.actionSearchResultsFragmentToCarDetailsFragment(item)
            findNavController().navigate(args)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchResultsBinding.inflate(layoutInflater).also {
        _binding = it
        setViews()
        setListeners()
    }.root

    private fun setViews() {

        binding.searchResultsList.adapter = carAdapter

        lifecycleScope.launch {
            searchResultsViewModel.searchResultsStateFlow.collect { records ->
                carAdapter.submitList(records.list)
                if (records.list.isNotEmpty()) {
                    hideProgressBar()
                }
            }
        }

        query = args.query

        if (query.isEmpty() || query !in resources.getStringArray(R.array.makes)) {
            showNoData()
            carAdapter.submitList(emptyList())
        } else {
            lifecycleScope.launch {
                searchResultsViewModel.get(query)
            }
        }
    }

    private fun setListeners() {
        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            lifecycleScope.launch {
                searchResultsViewModel.get(query)
            }
        }

        binding.fragmentSearchResultsToolbar.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun showError() {
        binding.noConnectionPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun showNoData() {
        binding.noDataPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideError() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
    }

    override fun showData() {
        binding.noDataPlaceHolder.root.visibility = View.GONE
    }

    override fun showProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _carAdapter = null
    }
}