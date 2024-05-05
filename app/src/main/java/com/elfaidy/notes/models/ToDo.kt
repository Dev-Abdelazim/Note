package com.elfaidy.notes.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ToDo(
    val title: String = "",
    var isChecked: Boolean = false
): Parcelable
