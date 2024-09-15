package com.example.autohub.domain.usecase

import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.repository.CarRepository

class GetCarsUseCase(private val carRepository: CarRepository) {

    suspend fun execute(): RecordsVo {
        return carRepository.getCars()
    }

}