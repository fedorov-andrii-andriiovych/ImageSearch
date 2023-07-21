package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun DetailedScreen(modifier: Modifier, mainViewModel: MainViewModel) {
    val context = LocalContext.current
    Scaffold(topBar = {
        DetailedTopAppBar(
            modifier = modifier,
            onSaveClicked = {
                val result = mainViewModel.saveImageToGallery()
                if (result) {
                    showToast(context, context.resources.getString(R.string.image_saved))
                } else {
                    showToast(context, context.resources.getString(R.string.image_dont_saved))
                }
            },
            title = mainViewModel.searchState,
        )

    }) {
        Column(modifier = modifier.padding(it)) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(1.dp)
            ) {}
            Box(modifier = modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                Box(modifier = modifier.background(Color.Black)) {
                    AsyncImage(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(mainViewModel.imageModelState.value.url)
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.icon_error),
                        placeholder = painterResource(id = R.drawable.icon_search),
                        contentDescription = stringResource(R.string.image),
                        contentScale = ContentScale.None
                    )
                }
            }
            Row(modifier = modifier.fillMaxWidth()) {
                IconButton(modifier = modifier
                    .weight(0.5f)
                    .background(Color.Black)
                    .border(1.dp, Color.White), onClick = { mainViewModel.lastImage() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_left),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                IconButton(modifier = modifier
                    .weight(0.5f)
                    .background(Color.Black)
                    .border(1.dp, Color.White), onClick = { mainViewModel.nextImage() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_right),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun DetailedTopAppBar(
    modifier: Modifier,
    title: State<String>,
    onSaveClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title.value.capitalize(),
                fontSize = 24.sp, color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            IconButton(onClick = {
                //Todo
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_star_full),
                    contentDescription = stringResource(R.string.star_full),
                    tint = Color.Yellow
                )
            }
            IconButton(onClick = {
                onSaveClicked()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_save),
                    contentDescription = stringResource(R.string.save)
                )
            }
        },
        backgroundColor = Color.Black,
        elevation = 8.dp,
        contentColor = Color.White
    )
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

