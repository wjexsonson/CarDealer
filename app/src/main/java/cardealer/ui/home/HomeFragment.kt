package com.example.autohub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.databinding.FragmentHomeBinding
import com.example.autohub.ui.activity.MainActivity
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.adapters.CarAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(), ScreenSwitchable {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _sortAdapter: ArrayAdapter<String>? = null
    private val sortAdapter get() = requireNotNull(_sortAdapter)

    private var _carAdapter: CarAdapter? = null
    private val carAdapter get() = requireNotNull(_carAdapter)

    private val homeViewModel by viewModel<HomeViewModel> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _sortAdapter = ArrayAdapter(
            requireContext(),
            R.layout.sort_spinner_item,
            resources.getStringArray(R.array.sort_array)
        )

        _carAdapter = CarAdapter { item ->
            val args = HomeFragmentDirections.actionHomeFragmentToCarDetailsFragment(item)
            findNavController().navigate(args)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(layoutInflater).also {
        lifecycle.addObserver((activity as MainActivity).MainBottomNavManager())
        _binding = it
        setViews()
        setListeners()
    }.root

    private fun setViews() {
        binding.sortSpinner.adapter = sortAdapter
        binding.carList.adapter = carAdapter

        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.carsStateFlow.collect { records ->
                    binding.let {
                        carAdapter.submitList(records.list)
                        it.carCounter.text = if (records.list.isEmpty()) {
                            "0 offers"
                        } else {
                            "${records.list.size} offers"
                        }
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.let {
                    val selectedSortFilter =
                        when (parent.getItemAtPosition(position).toString()) {
                            "mileage min" -> "mileage:asc"
                            "price min" -> "price:asc"
                            "price max" -> "price:desc"
                            else -> ""
                        }
                    if (selectedSortFilter.isEmpty()) homeViewModel.get()
                    else homeViewModel.sort(selectedSortFilter)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            homeViewModel.get()
        }

        binding.fragmentHomeToolbar.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    override fun showError() {
        binding.noConnectionPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun showNoData() {
        binding.carList.visibility = View.GONE
    }

    override fun hideError() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
    }

    override fun showData() {
        binding.carList.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        with(binding) {
            noConnectionPlaceHolder.root.visibility = View.GONE
            progressBarPlaceHolder.root.visibility = View.VISIBLE
        }
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
        _sortAdapter = null
        _carAdapter = null
    }
}