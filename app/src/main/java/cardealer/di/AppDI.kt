package com.example.autohub.di

import com.example.autohub.ui.activity.MainActivityViewModel
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.authentication.signin.SignInViewModel
import com.example.autohub.ui.authentication.signup.SignUpViewModel
import com.example.autohub.ui.cardetails.CarDetailsViewModel
import com.example.autohub.ui.favourite.FavouriteViewModel
import com.example.autohub.ui.home.HomeViewModel
import com.example.autohub.ui.search.search.SearchViewModel
import com.example.autohub.ui.search.searchresults.SearchResultsViewModel
import com.example.autohub.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            getCarsUseCase = get(),
            sortCarsUseCase = get()
        )
    }

    viewModel<SignInViewModel> {
        SignInViewModel(signInUseCase = get(), getCurrentUserUseCase = get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(signUpUseCase = get())
    }

    viewModel<CarDetailsViewModel> {
        CarDetailsViewModel(
            checkIfCarIsFavoutriteUseCase = get(),
            addToFavouriteUseCase = get(),
            deleteFromFavouriteUseCase = get(),
            getCurrentUserUseCase = get()
        )
    }

    viewModel<FavouriteViewModel> {
        FavouriteViewModel(getFavouritesUseCase = get(), getCurrentUserUseCase = get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(
            loadSearchHistoryUseCase = get(),
            updateSearchHistoryUseCase = get(),
            clearSearchHistoryUseCase = get()
        )
    }

    viewModel<SearchResultsViewModel> { (screenSwitchable: ScreenSwitchable) ->
        SearchResultsViewModel(searchCarsByMakeUseCase = get(), screenSwitchable = screenSwitchable)
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(
            signOutUseCase = get(),
            getCurrentUserUseCase = get(),
            getAppThemeUseCase = get(),
            changeAppThemeUseCase = get()
        )
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getAppThemeUseCase = get())
    }
}