package com.example.autohub.di

import com.example.autohub.domain.usecase.AddToFavouriteUseCase
import com.example.autohub.domain.usecase.ChangeAppThemeUseCase
import com.example.autohub.domain.usecase.CheckIfCarIsFavoutriteUseCase
import com.example.autohub.domain.usecase.ClearSearchHistoryUseCase
import com.example.autohub.domain.usecase.DeleteFromFavouriteUseCase
import com.example.autohub.domain.usecase.GetAppThemeUseCase
import com.example.autohub.domain.usecase.GetCarsUseCase
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.GetFavouritesUseCase
import com.example.autohub.domain.usecase.LoadSearchHistoryUseCase
import com.example.autohub.domain.usecase.SearchCarsByMakeUseCase
import com.example.autohub.domain.usecase.SignInUseCase
import com.example.autohub.domain.usecase.SignOutUseCase
import com.example.autohub.domain.usecase.SignUpUseCase
import com.example.autohub.domain.usecase.SortCarsUseCase
import com.example.autohub.domain.usecase.UpdateSearchHistoryUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCarsUseCase> {
        GetCarsUseCase(carRepository = get())
    }

    factory<SortCarsUseCase> {
        SortCarsUseCase(carRepository = get())
    }

    factory<SearchCarsByMakeUseCase> {
        SearchCarsByMakeUseCase(carRepository = get())
    }

    factory<GetFavouritesUseCase> {
        GetFavouritesUseCase(carDetailsRepository = get())
    }

    factory<AddToFavouriteUseCase> {
        AddToFavouriteUseCase(carDetailsRepository = get())
    }

    factory<CheckIfCarIsFavoutriteUseCase> {
        CheckIfCarIsFavoutriteUseCase(carDetailsRepository = get())
    }

    factory<ClearSearchHistoryUseCase> {
        ClearSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<DeleteFromFavouriteUseCase> {
        DeleteFromFavouriteUseCase(carDetailsRepository = get())
    }

    factory<GetCurrentUserUseCase> {
        GetCurrentUserUseCase(authReposotory = get())
    }

    factory<LoadSearchHistoryUseCase> {
        LoadSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<SignInUseCase> {
        SignInUseCase(authReposotory = get())
    }

    factory<SignOutUseCase> {
        SignOutUseCase(authReposotory = get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(authReposotory = get())
    }

    factory<UpdateSearchHistoryUseCase> {
        UpdateSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<GetAppThemeUseCase> {
        GetAppThemeUseCase(settingsRepository = get())
    }

    factory<ChangeAppThemeUseCase> {
        ChangeAppThemeUseCase(settingsRepository = get())
    }
}