package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.BottomNavigation
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.Screens
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.navigationparams.navigate
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.navigationparams.toBundle
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.navigationparams.toDetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.DetailedViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.FavoriteViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel

@Composable
fun HomeScreen(onShareClicked: (String) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box(Modifier.padding(bottom = it.calculateBottomPadding())) {
            NavHost(navController = navController, startDestination = Screens.MAIN.route) {
                composable(Screens.MAIN.route) {
                    MainScreen(
                        modifier = Modifier,
                        mainViewModel = hiltViewModel<MainViewModel>(),
                        onDetailedClicked = { detailParams ->
                            navController.navigate(Screens.DETAILED.route,detailParams.toBundle())
                        })
                }
                composable(Screens.DETAILED.route) {host->
                    val detailParams = host.arguments?.toDetailParams()!!
                    DetailedScreen(
                        modifier = Modifier,
                        detailedViewModel = hiltViewModel<DetailedViewModel>(),
                        onShareClicked = { url -> onShareClicked(url) },
                        detailParams = detailParams
                    )
                }
                composable(Screens.FAVORITE.route) {
                    FavoriteScreen(
                        modifier = Modifier,
                        favoriteViewModel = hiltViewModel<FavoriteViewModel>(),
                        onDetailedClicked = { detailParams ->
                            navController.navigate(Screens.DETAILED.route,detailParams.toBundle())
                        })
                }
                composable(Screens.SETTINGS.route) {
                    SettingsScreen(settingsViewModel = hiltViewModel<FavoriteViewModel>())
                }
            }
        }
    }
}