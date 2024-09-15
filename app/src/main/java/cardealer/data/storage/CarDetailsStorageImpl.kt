package com.example.autohub.data.storage

import com.example.autohub.data.storage.model.CarDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarDetailsStorageImpl : CarDetailsStorage {

    private val dataBase = FirebaseFirestore.getInstance()
    override fun getFavourites(): StateFlow<List<CarDto>> {
        val favouritesListStateFlow = MutableStateFlow<List<CarDto>>(emptyList())
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            favouritesListStateFlow.value = emptyList()
            return favouritesListStateFlow
        }

        dataBase.collection("users")
            .document(currentUser.uid)
            .collection("favourite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    val favouriteList = value.documents.mapNotNull { it.toObject(CarDto::class.java) }
                    favouritesListStateFlow.value = favouriteList
                } else {
                    favouritesListStateFlow.value = emptyList()
                }
            }
        return favouritesListStateFlow
    }

    override fun checkIfCarIsFavoutrite(carDto: CarDto): StateFlow<Boolean> {
        val isFavouriteStateFlow = MutableStateFlow(false)
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            isFavouriteStateFlow.value = false
            return isFavouriteStateFlow
        }

        dataBase.collection("users")
            .document(currentUser.uid)
            .collection("favourite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                value?.documents?.map { it.toObject(CarDto::class.java)!! }?.let {
                    isFavouriteStateFlow.value = it.contains(carDto)
                } ?: run {
                    isFavouriteStateFlow.value = false
                }
            }
        return isFavouriteStateFlow
    }

    override fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            dataBase.collection("users").document(currentUser.uid)
                .collection("favourite").document(id).set(carMap)
        }
    }

    override fun deleteFromFavourite(id: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            dataBase.collection("users").document(currentUser.uid)
                .collection("favourite").document(id).delete()
        }
    }
}
