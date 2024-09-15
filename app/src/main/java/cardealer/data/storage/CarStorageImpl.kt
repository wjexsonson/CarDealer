package com.example.autohub.data.storage

import com.example.autohub.data.api.RetrofitCarInstance
import com.example.autohub.data.storage.model.RecordsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarStorageImpl : CarStorage {

    override suspend fun getCars(): RecordsDto {
        return withContext(Dispatchers.IO) {
            RetrofitCarInstance.api.getCars().body() ?: RecordsDto(list = emptyList())
        }
    }

    override suspend fun searchCarsByMake(make: String): RecordsDto {
        return withContext(Dispatchers.IO) {
            RetrofitCarInstance.api.searchCarsByMake(make).body()
                ?: RecordsDto(list = emptyList())
        }
    }

    override suspend fun sortCars(sortFilter: String): RecordsDto {
        return withContext(Dispatchers.IO) {
            RetrofitCarInstance.api.sortCars(sortFilter).body()
                ?: RecordsDto(list = emptyList())
        }
    }
}