package com.example.autohub.domain.repository

import com.example.autohub.domain.model.RecordsVo

interface CarRepository {
    suspend fun getCars(): RecordsVo

    suspend fun searchCarsByMake(make: String): RecordsVo

    suspend fun sortCars(sortFilter: String): RecordsVo
}