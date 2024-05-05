package com.elfaidy.notes.utils

import androidx.compose.ui.graphics.Color
import java.text.DateFormat
import java.util.Calendar

object Constants {

    const val MAX_TEXT_SIZE: Int = 26
    const val MIN_TEXT_SIZE: Int = 16

    /*
    * get local time and date function
    * */
    fun getDateAndTime(): String {
        val calendar = Calendar.getInstance().time
        return DateFormat.getDateTimeInstance().format(calendar)
    }

    val backgroundColors: List<Color> = listOf(
        Color(0XFFFAEED1),
        Color(0XFFFFC5C5),
        Color(0XFFCDF5FD),
        Color(0XFFFEFF86),
        Color(0XFFE5BA73),
        Color(0XFFDEBACE),
        Color(0XFFFF87B2),
        Color(0XFFC499BA),
        Color(0XFFBCFFB9),
        Color(0XFFFF6701),
    )


    fun increaseTextSize(textSize: Int): Int = textSize + 2
    fun decreaseTextSize(textSize: Int): Int = textSize - 2

}