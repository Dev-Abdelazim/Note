package com.elfaidy.notes.ui.screens.notesscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.elfaidy.notes.R
import com.elfaidy.notes.viewmodel.MainViewModel

@Composable
fun SearchTextField(viewModel: MainViewModel) {
    OutlinedTextField(
        value = viewModel.search.value,
        onValueChange = {
            // add action when user write input in text field
            viewModel.search.value = it
        },
        label = {
            Text(
                text = stringResource(id = R.string.search_label)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search_label)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.search_text_field_padding)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.search_text_field_corner_size))
    )
}