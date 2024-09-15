package com.example.autohub.domain.usecase

import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.repository.CarDetailsRepository
import kotlinx.coroutines.flow.StateFlow

class CheckIfCarIsFavoutriteUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(carVo: CarVo): StateFlow<Boolean>{
        return carDetailsRepository.checkIfCarIsFavoutrite(carVo)
    }
}