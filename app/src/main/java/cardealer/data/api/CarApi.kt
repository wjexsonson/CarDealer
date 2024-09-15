package com.example.autohub.data.api

import com.example.autohub.data.storage.model.RecordsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

    @GET("listings")
    suspend fun searchCarsByMake(@Query("make") make: String): Response<RecordsDto>

    @GET("listings")
    suspend fun getCars(): Response<RecordsDto>

    @GET("listings")
    suspend fun sortCars(@Query("sort_filter") sortFilter: String): Response<RecordsDto>
}