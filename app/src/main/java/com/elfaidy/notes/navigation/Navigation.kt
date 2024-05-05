@file:OptIn(ExperimentalGlideComposeApi::class)

package com.elfaidy.notes.navigation

import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.elfaidy.notes.ui.screens.addupdatescreen.AddUpdateScreen
import com.elfaidy.notes.ui.screens.notesscreen.NotesScreen
import com.elfaidy.notes.ui.screens.splashscreen.SplashScreen
import com.elfaidy.notes.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(
    viewModel: MainViewModel,
    multiplePermissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.NotesScreen.route
    ){

        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navController)
        }

        composable(route = Screens.NotesScreen.route){
            NotesScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(
            route = "${Screens.AddUpdateScreen.route}?id={id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ){
           val id = it.arguments?.getLong("id")
            AddUpdateScreen(
                viewModel = viewModel,
                navController = navController,
                multiplePermissionLauncher,
                id = id ?: 0
            )
        }
    }

}