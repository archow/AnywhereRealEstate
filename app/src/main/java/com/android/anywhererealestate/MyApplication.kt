package com.android.anywhererealestate

import android.app.Application
import com.android.anywhererealestate.di.AppContainer

class MyApplication : Application() {
    val appContainer = AppContainer(this)
}