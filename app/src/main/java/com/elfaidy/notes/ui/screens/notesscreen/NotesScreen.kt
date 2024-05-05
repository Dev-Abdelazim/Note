package com.elfaidy.notes.ui.screens.notesscreen

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.elfaidy.notes.R
import com.elfaidy.notes.components.NoteTopAppBar
import com.elfaidy.notes.navigation.Screens
import com.elfaidy.notes.ui.screens.notesscreen.components.EmptyContentPlaceHolder
import com.elfaidy.notes.ui.screens.notesscreen.components.NoteItem
import com.elfaidy.notes.ui.screens.notesscreen.components.NotesScreenFAB
import com.elfaidy.notes.ui.screens.notesscreen.components.SearchTextField
import com.elfaidy.notes.ui.theme.NoteAppThemeSettings
import com.elfaidy.notes.viewmodel.MainViewModel

@ExperimentalGlideComposeApi
@Composable
fun NotesScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    NotesScreenContent(
        viewModel = viewModel,
        navController = navController
    )
}





@ExperimentalGlideComposeApi
@Composable
fun NotesScreenContent(
    viewModel: MainViewModel,
    navController: NavController
) {

    val actionIcon = if (NoteAppThemeSettings.isInDarkTheme.value || isSystemInDarkTheme()){
        R.drawable.baseline_light_mode_24
    }else{
        R.drawable.baseline_brightness_2_24
    }

    val systemInDarkMode = isSystemInDarkTheme()
    val context = LocalContext.current
    val message = stringResource(id = R.string.change_theme_error_message)

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(id = R.string.notes_screen_top_app_bar_title),
                actionIcons = listOf(actionIcon),
                actionIconsClick = {
                    // here you can change the theme by click this icon
                    if (systemInDarkMode){
                        NoteAppThemeSettings.isInDarkTheme.value = true
                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        NoteAppThemeSettings.isInDarkTheme.value = !NoteAppThemeSettings.isInDarkTheme.value
                    }
                }
            )
        },
        floatingActionButton = {
            NotesScreenFAB {
                navController.navigate(route = Screens.AddUpdateScreen.route)
            }
        },

        ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SearchTextField(viewModel)

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.notes_screen_divider_padding)
                    )
            )

            if (viewModel.notes.value.isNotEmpty()){
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // show list of notes in the home screen
                    items(viewModel.notes.value){ note ->
                        NoteItem(note){
                            // go to edit note when click it
                            viewModel.isEditNote.value = true
                            viewModel.setAppData(note)
                            navController.navigate("${Screens.AddUpdateScreen.route}?id=${note.id}")
                        }
                    }
                }

            }else{
                EmptyContentPlaceHolder()
            }
        }
    }
}




