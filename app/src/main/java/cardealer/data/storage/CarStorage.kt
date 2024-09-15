package com.example.autohub.data.storage

import com.example.autohub.data.storage.model.RecordsDto

interface CarStorage {
    suspend fun getCars(): RecordsDto

    suspend fun searchCarsByMake(make: String): RecordsDto

    suspend fun sortCars(sortFilter: String): RecordsDto
}