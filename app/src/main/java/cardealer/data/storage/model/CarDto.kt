package com.example.autohub.data.storage.model

import com.google.gson.annotations.SerializedName

data class CarDto(
    @SerializedName("bodyType")
    val bodyType: String = "",
    @SerializedName("condition")
    val condition: String = "",
    @SerializedName("displayColor")
    val displayColor: String? = "",
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("make")
    val make: String = "",
    @SerializedName("mileage")
    val mileage: String = "",
    @SerializedName("model")
    val model: String = "",
    @SerializedName("photoUrls")
    val photoUrls: List<String> = emptyList(),
    @SerializedName("price")
    val price: String = "",
    @SerializedName("primaryPhotoUrl")
    val primaryPhotoUrl: String = "",
    @SerializedName("vin")
    val vin: String = "",
    @SerializedName("year")
    val year: Int = -1
)