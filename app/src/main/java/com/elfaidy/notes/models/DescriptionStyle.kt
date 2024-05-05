package com.elfaidy.notes.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DescriptionStyle(
    var fontSize: Int = 16,
    var isBold: Boolean = false,
    var isItalic: Boolean = false
):Parcelable
