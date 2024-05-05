package com.elfaidy.notes.ui.screens.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.elfaidy.notes.R
import com.elfaidy.notes.navigation.Screens

@Composable
fun SplashScreen(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anime))
    var isLoading by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isLoading
    )

    LaunchedEffect(key1 = progress){
        if (progress == 0f){
            isLoading = true
        }
        if (progress == 1f){
            navController.navigate(route = Screens.NotesScreen.route)
        }
    }


    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        LottieAnimation(
            modifier = Modifier.size(300.dp),
            composition = composition,
            progress = {progress}
        )

    }
}