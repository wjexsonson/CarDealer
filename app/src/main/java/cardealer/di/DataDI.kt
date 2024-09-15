package com.example.autohub.di

import com.example.autohub.data.repository.AuthRepositoryImpl
import com.example.autohub.data.repository.CarDetailsRepositoryImpl
import com.example.autohub.data.repository.CarRepositoryImpl
import com.example.autohub.data.repository.SearchHistoryRepositoryImpl
import com.example.autohub.data.repository.SettingsRepositoryImpl
import com.example.autohub.data.storage.AuthStorage
import com.example.autohub.data.storage.AuthStorageImpl
import com.example.autohub.data.storage.CarDetailsStorage
import com.example.autohub.data.storage.CarDetailsStorageImpl
import com.example.autohub.data.storage.CarStorage
import com.example.autohub.data.storage.CarStorageImpl
import com.example.autohub.data.storage.SearchHistoryStorage
import com.example.autohub.data.storage.SearchHistoryStorageImpl
import com.example.autohub.data.storage.SettingsStorageImpl
import com.example.autohub.data.storage.SettingsStorage
import com.example.autohub.domain.repository.AuthReposotory
import com.example.autohub.domain.repository.CarDetailsRepository
import com.example.autohub.domain.repository.CarRepository
import com.example.autohub.domain.repository.SearchHistoryRepository
import com.example.autohub.domain.repository.SettingsRepository
import org.koin.dsl.module

val dataModule = module {

    single<CarStorage> {
        CarStorageImpl()
    }

    single<CarRepository> {
        CarRepositoryImpl(carStorage = get())
    }

    single<AuthStorage> {
        AuthStorageImpl()
    }

    single<AuthReposotory> {
        AuthRepositoryImpl(authStorage = get())
    }

    single<CarDetailsStorage> {
        CarDetailsStorageImpl()
    }

    single<CarDetailsRepository> {
        CarDetailsRepositoryImpl(carDetailsStorage = get())
    }

    single<SearchHistoryStorage> {
        SearchHistoryStorageImpl(context = get())
    }

    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl(searchHistoryStorage = get())
    }

    single<SettingsStorage> {
        SettingsStorageImpl(context = get())
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(settingsStorage = get())
    }
}