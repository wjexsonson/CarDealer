package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.CarDetailsRepository

class DeleteFromFavouriteUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(id: String){
        carDetailsRepository.deleteFromFavourite(id)
    }
}