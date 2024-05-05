package com.elfaidy.notes.ui.screens.notesscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.elfaidy.notes.R
import com.elfaidy.notes.data.entity.NoteEntity
import com.elfaidy.notes.ui.screens.addupdatescreen.components.ToDoItem

@ExperimentalGlideComposeApi
@Composable
fun NoteItem(
    note: NoteEntity,
    onClick: () -> Unit
) {



    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.card_note_item_padding))
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.card_note_item_corner_size)
        ),
        elevation = CardDefaults.cardElevation(
            dimensionResource(id = R.dimen.card_note_item_elevation),
        ),
        colors = CardDefaults.cardColors(
            containerColor = note.color.color
        )
    ) {

        // if there an image added in top of card
        if (note.images.isNotEmpty()){
            GlideImage(
                model = note.images.first(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 150.dp),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.card_content_item_padding))
        ){

            // note title
            Text(text = note.title,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.card_content_title_padding_bottom)
                ),
                style = MaterialTheme.typography.titleMedium
            )

            // note date and time
            Text(
                text = note.date,
                style = MaterialTheme.typography.labelSmall
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.card_content_divider_vertical_padding),
                        bottom = dimensionResource(id = R.dimen.card_content_divider_vertical_padding),
                        end = dimensionResource(id = R.dimen.card_content_divider_end_padding)
                    )
            )

            Text(
                text = note.description,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp
            )
        }
    }
}