package com.elfaidy.notes.ui.screens.notesscreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.elfaidy.notes.R

@Composable
fun NotesScreenFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.padding(
            end = dimensionResource(id = R.dimen.fab_end_padding),
            bottom = dimensionResource(id = R.dimen.fab_start_padding)
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_new_icon_desc),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}