package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elfaidy.notes.R
import com.elfaidy.notes.viewmodel.MainViewModel

@Composable
fun AddUpdateHeader(
    viewModel: MainViewModel,
    modifier: Modifier,
    newBackground: Color
) {

    Column(
        modifier = modifier,
    ) {
        AddUpdateTextField(
            value = viewModel.title.value,
            placeholder = stringResource(id = R.string.title),
            placeholderStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.title_text_field_top_padding)
                ),
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            ),
            containerColor = newBackground
        ){ newTitle -> viewModel.title.value = newTitle }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 16.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = viewModel.date,
                style = MaterialTheme.typography.labelSmall,
            )

            Text(
                text ="${viewModel.charCounter.value} " + "character",
                style = MaterialTheme.typography.labelSmall,
            )
        }

        Divider( Modifier.padding(8.dp) )
    }
}
