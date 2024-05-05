@file:OptIn(ExperimentalMaterial3Api::class)

package com.elfaidy.notes.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun NoteTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes actionIcons: List<Int>,
    navigationIcon: ImageVector? = null,
    navigationIconClick: () -> Unit = {},
    actionIconsClick: (Int) -> Unit = {}
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title
            )
        },
        actions = {
            actionIcons.forEach{
                IconButton(onClick = { actionIconsClick(it) }) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                }
            }
        },
        navigationIcon = {
            if (navigationIcon != null){
                IconButton(onClick = { navigationIconClick() }) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIcon.name
                    )
                }
            }
        }
    )
}