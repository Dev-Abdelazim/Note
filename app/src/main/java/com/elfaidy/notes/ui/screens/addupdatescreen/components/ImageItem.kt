@file:OptIn(ExperimentalGlideComposeApi::class)

package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.elfaidy.notes.R

@Composable
fun ImageItem(imageUri: String,  modifier: Modifier ,onClick: () -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ){

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 5.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ){
            GlideImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        IconButton( onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            ),
            modifier = Modifier.size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = null
            )
        }
    }
}
