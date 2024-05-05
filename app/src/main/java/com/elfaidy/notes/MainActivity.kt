package com.elfaidy.notes

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.compose.hiltViewModel
import com.elfaidy.notes.ui.screens.addupdatescreen.components.CameraPermissionTextProvider
import com.elfaidy.notes.ui.screens.addupdatescreen.components.GalleryPermissionTextProvider
import com.elfaidy.notes.ui.screens.addupdatescreen.components.PermissionDialog
import com.elfaidy.notes.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val dialogQueue = viewModel.visiblePermissionQueue

            val multiplePermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = {perms ->
                    perms.keys.forEach {permission ->
                        viewModel.onPermissionResult(
                            permission = permission,
                            isGranted = perms[permission] == true
                        )
                    }
                }
            )
            NotesApp(multiplePermissionLauncher, viewModel)
            
            dialogQueue
                .reversed()
                .forEach {permission ->
                    PermissionDialog(
                        permissionTextProvider = when(permission){
                            Manifest.permission.CAMERA -> {
                                CameraPermissionTextProvider()
                            }

                            Manifest.permission.READ_MEDIA_IMAGES -> {
                                GalleryPermissionTextProvider()
                            }

                            else -> return@forEach
                        },
                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                            permission
                        ),
                        onDismiss = viewModel::dismissDialog,
                        onOkClick = {
                            viewModel.dismissDialog()
                            multiplePermissionLauncher.launch(
                                arrayOf(permission)
                            )
                        },
                        onGoToSettingsClicked = {
                            openAppSettings()
                            viewModel.dismissDialog()
                        }
                    )
                }
        }
    }
}

fun Activity.openAppSettings(){
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

