package com.example.autohub.ui.cardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.autohub.R
import com.example.autohub.databinding.FragmentCarBinding
import com.example.autohub.ui.adapters.CarPhotosAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarDetailsFragment : Fragment() {

    private var _binding: FragmentCarBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _carPhotosAdapter: CarPhotosAdapter? = null
    private val carPhotosAdapter get() = requireNotNull(_carPhotosAdapter)

    private val args: CarDetailsFragmentArgs by navArgs()

    private val carDetailsViewModel by viewModel<CarDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _carPhotosAdapter = CarPhotosAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCarBinding.inflate(layoutInflater).also {
        _binding = it
        setViews()
        setListeners()
    }.root

    private fun setViews() {
        binding.carTitleContainer.carPhotos.adapter = carPhotosAdapter

        with(binding.carCharacteristicsContainer) {
            year.text = args.car.year.toString()
            body.text = args.car.bodyType
            mileage.text = args.car.mileage
            color.text = args.car.displayColor
            vin.text = args.car.vin
        }

        with(binding.carTitleContainer) {
            carInfo.text =
                getString(R.string.car_info, args.car.make, args.car.model, args.car.year)
            price.text = args.car.price
        }

        carPhotosAdapter.submitList(args.car.photoUrls)

        var isFavorite = false

        lifecycleScope.launch {
            carDetailsViewModel.checkIfCarIsFavoutrite(args.car)
                .collect { isInFavouriteList ->
                    isFavorite = isInFavouriteList
                    binding.fragmentCarToolbar.favIcon.setBackgroundResource(if (isFavorite) R.drawable.favorite else R.drawable.favorite_border)
                }
        }

        binding.fragmentCarToolbar.favIcon.setOnClickListener {
            lifecycleScope.launch {
                carDetailsViewModel.getCurrentUser().collect { user ->
                    if (user == null) {
                        Toast.makeText(
                            requireContext(),
                            "Log in to add cars to your favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@collect
                    }
                    isFavorite = !isFavorite
                    if (!isFavorite) {
                        carDetailsViewModel.deleteFromFavourite(args.car.id.toString())
                        binding.fragmentCarToolbar.favIcon.setBackgroundResource(R.drawable.favorite_border)
                        return@collect
                    }
                    carDetailsViewModel.addToFavourite(args.car.id.toString(), getCarMap())
                    binding.fragmentCarToolbar.favIcon.setBackgroundResource(R.drawable.favorite)
                }
            }
        }
    }

    private fun setListeners() {
        binding.fragmentCarToolbar.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getCarMap(): HashMap<String, Any> {
        return hashMapOf(
            "bodyType" to args.car.bodyType,
            "condition" to args.car.condition,
            "displayColor" to args.car.displayColor,
            "id" to args.car.id,
            "make" to args.car.make,
            "mileage" to args.car.mileage,
            "model" to args.car.model,
            "photoUrls" to args.car.photoUrls,
            "price" to args.car.price,
            "primaryPhotoUrl" to args.car.primaryPhotoUrl,
            "vin" to args.car.vin,
            "year" to args.car.year
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _carPhotosAdapter = null
    }
}
