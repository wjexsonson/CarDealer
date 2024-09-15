package com.example.autohub.domain.repository

import com.example.autohub.domain.model.CarVo
import kotlinx.coroutines.flow.StateFlow

interface CarDetailsRepository {
    fun getFavourites(): StateFlow<List<CarVo>>
    fun checkIfCarIsFavoutrite(carVo: CarVo): StateFlow<Boolean>
    fun addToFavourite(id: String, carMap: HashMap<String, Any>)
    fun deleteFromFavourite(id: String)
}