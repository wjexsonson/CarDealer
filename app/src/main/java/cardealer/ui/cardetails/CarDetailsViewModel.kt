package com.example.autohub.ui.cardetails

import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.usecase.AddToFavouriteUseCase
import com.example.autohub.domain.usecase.CheckIfCarIsFavoutriteUseCase
import com.example.autohub.domain.usecase.DeleteFromFavouriteUseCase
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarDetailsViewModel(
    private val checkIfCarIsFavoutriteUseCase: CheckIfCarIsFavoutriteUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun checkIfCarIsFavoutrite(carVo: CarVo): StateFlow<Boolean> {
        return checkIfCarIsFavoutriteUseCase.execute(carVo)
    }

    fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        addToFavouriteUseCase.execute(id, carMap)
    }

    fun deleteFromFavourite(id: String) {
        deleteFromFavouriteUseCase.execute(id)
    }

    fun getCurrentUser(): StateFlow<FirebaseUser?> {
        val user = MutableStateFlow<FirebaseUser?>(null)
        user.value = getCurrentUserUseCase.execute()
        return user
    }

}