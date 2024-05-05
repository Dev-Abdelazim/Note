package com.elfaidy.notes.ui.screens.notesscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.elfaidy.notes.R


@Composable
fun EmptyContentPlaceHolder() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier.size(
                dimensionResource(id = R.dimen.place_holder_icon_size)
            ),
            colorFilter = ColorFilter.tint(
                Color.LightGray.copy(0.5f)
            )
        )

        Text(
            text = stringResource(id = R.string.message),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray.copy(0.9f)
        )

    }
}