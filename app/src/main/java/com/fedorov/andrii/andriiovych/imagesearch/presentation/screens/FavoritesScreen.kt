package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.ImageVerticalGrid
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.MainTopAppBar
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.FavoritesViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground

@Composable
fun FavoritesScreen(modifier: Modifier, favoritesViewModel: FavoritesViewModel) {

    val listImage = favoritesViewModel.stateFavoriteList.collectAsState(initial = emptyList())

    Scaffold(topBar = {
        MainTopAppBar(title = stringResource(R.string.favorites))
    }) {

        Box(modifier = modifier
            .padding(it)
            .background(SettingsBackground)
            .fillMaxSize(),
            ) {

            ImageVerticalGrid(
                modifier = Modifier,
                listImages = listImage.value,
                onDetailedClicked = {
                    //Todo
                },
                onStarClicked = { model ->
                    favoritesViewModel.deleteImage(imageModel = model)
                }
            )
        }
    }
}


