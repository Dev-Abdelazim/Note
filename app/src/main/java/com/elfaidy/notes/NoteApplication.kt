package com.elfaidy.notes

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication: Application() {

    init {
        context = this
    }

    companion object{
        lateinit var context: Context
    }
}