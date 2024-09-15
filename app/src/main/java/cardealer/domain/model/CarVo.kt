package com.example.autohub.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarVo(
    val bodyType: String = "",
    val condition: String = "",
    val displayColor: String = "",
    val id: Int = -1,
    val make: String = "",
    val mileage: String = "",
    val model: String = "",
    val photoUrls: List<String> = emptyList(),
    val price: String = "",
    val primaryPhotoUrl: String = "",
    val vin: String = "",
    val year: Int = -1
) : Parcelable  