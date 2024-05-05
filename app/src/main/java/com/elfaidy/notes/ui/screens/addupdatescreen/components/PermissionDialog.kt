package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToSettingsClicked: () -> Unit
) {

    AlertDialog(
        modifier = modifier,
        title = {
            Text(
                text = "Permission required"
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermanentlyDeclined){
                        "Grant permission"
                    }else{
                        "OK"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToSettingsClicked()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp)
               )

            }
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(isPermanentlyDeclined)
            )
        }

    )
}


interface PermissionTextProvider{
    fun getDescription(isPermanentlyDeclined: Boolean):String
}

class CameraPermissionTextProvider: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined camera permission. " +
                    "You can go to the app settings to grant it"
        } else {
            "This app need access to your camera to take a photo " +
                    "and add it in note"
        }
    }
}


class GalleryPermissionTextProvider: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined read images permission. " +
                    "You can go to the app settings to grant it"
        } else {
            "This app need access to your gallery to choice an image " +
                    "and add it in note"
        }
    }
}