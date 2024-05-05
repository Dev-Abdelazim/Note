package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun BottomBar(
    icons: List<IconModel>,
    modifier: Modifier,
    onIconClick: (Int) -> Unit
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        icons.forEach{ icon ->
            IconButton(onClick = { onIconClick.invoke(icon.icon) }) {
                Icon(
                    painter = painterResource(id = icon.icon),
                    contentDescription = stringResource(id = icon.desc)
                )
            }
        }
    }
}