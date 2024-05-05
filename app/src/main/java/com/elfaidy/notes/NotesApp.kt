package com.elfaidy.notes

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.elfaidy.notes.navigation.Navigation
import com.elfaidy.notes.ui.theme.NotesTheme
import com.elfaidy.notes.viewmodel.MainViewModel

@Composable
fun NotesApp(
    multiplePermissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    viewModel: MainViewModel
) {
    NotesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigation(viewModel, multiplePermissionLauncher)
        }
    }
}