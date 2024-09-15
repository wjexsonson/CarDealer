package com.example.autohub.domain.usecase

import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.repository.CarRepository

class SearchCarsByMakeUseCase(private val carRepository: CarRepository) {
    suspend fun execute(make: String): RecordsVo {
        return carRepository.searchCarsByMake(make)
    }
}