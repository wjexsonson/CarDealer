package com.example.autohub.domain.usecase

import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.repository.CarDetailsRepository
import kotlinx.coroutines.flow.StateFlow

class GetFavouritesUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(): StateFlow<List<CarVo>> {
        return carDetailsRepository.getFavourites()
    }
}