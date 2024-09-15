package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.CarDetailsRepository

class AddToFavouriteUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(id: String, carMap: HashMap<String, Any>) {
        carDetailsRepository.addToFavourite(id, carMap)
    }
}