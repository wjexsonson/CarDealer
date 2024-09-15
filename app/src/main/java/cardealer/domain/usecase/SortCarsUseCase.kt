package com.example.autohub.domain.usecase

import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.repository.CarRepository

class SortCarsUseCase(private val carRepository: CarRepository) {
    suspend fun execute(sortFilter: String): RecordsVo {
        return carRepository.sortCars(sortFilter)
    }
}