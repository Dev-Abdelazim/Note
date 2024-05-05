package com.elfaidy.notes.navigation

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object NotesScreen: Screens("notes_screen")
    object AddUpdateScreen: Screens("add_screen")
}