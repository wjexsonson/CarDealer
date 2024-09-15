package com.example.autohub.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.autohub.databinding.FragmentFavouriteBinding
import com.example.autohub.ui.adapters.CarAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _carAdapter: CarAdapter? = null
    private val carAdapter get() = requireNotNull(_carAdapter)

    private val favouriteViewModel by viewModel<FavouriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _carAdapter = CarAdapter { item ->
            val args = FavouriteFragmentDirections.actionFavouriteFragmentToCarDetailsFragment(item)
            findNavController().navigate(args)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFavouriteBinding.inflate(layoutInflater).also {
        _binding = it
        setViews()
    }.root

    private fun setViews() {
        binding.favouriteList.adapter = carAdapter
        favouriteViewModel.refreshCurrentUser()

        viewLifecycleOwner.lifecycleScope.launch {
            favouriteViewModel.currentUser.collect { user ->
                binding.notAuthorizedUserPlaceHolder.root.isVisible = user == null
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favouriteViewModel.carsStateFlow.collect { favouriteList ->
                carAdapter.submitList(favouriteList)
                binding.emptyFavouritePlaceHolder.root.isVisible = carAdapter.itemCount == 0
            }
        }
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