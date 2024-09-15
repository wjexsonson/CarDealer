package com.example.autohub.ui.search.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.autohub.databinding.FragmentSearchBinding
import com.example.autohub.ui.adapters.SearchHistoryAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _searchHistoryAdapter: SearchHistoryAdapter? = null
    private val searchHistoryAdapter get() = requireNotNull(_searchHistoryAdapter)

    private lateinit var searchEditText: EditText

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _searchHistoryAdapter = SearchHistoryAdapter { item ->
            val args =
                SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(
                    item.query
                )
            findNavController().navigate(args)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater).also {
            _binding = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.searchHistory.collect { history ->
                searchHistoryAdapter.submitList(history)
            }
        }

        lifecycleScope.launch {
            viewModel.loadSearchHistory()
        }

        searchEditText = binding.searchEt

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.cancelButton.visibility = if (text?.isNotEmpty() == true) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

            override fun afterTextChanged(text: Editable?) {
                binding.searchButton.setOnClickListener {
                    val query = text.toString()
                    lifecycleScope.launch {
                        viewModel.updateSearchHistory(query)
                    }
                    val args =
                        SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(query)
                    findNavController().navigate(args)
                }
            }
        })

        binding.cancelButton.setOnClickListener {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

            imm?.let { inputManager ->
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                searchEditText.text.clear()
            }
        }

        binding.clearSearchHistoryButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.clearSearchHistory()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _searchHistoryAdapter = null
    }
}
