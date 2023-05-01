package com.android.anywhererealestate.di

import android.content.Context
import com.android.anywhererealestate.network.CharactersApi
import com.android.anywhererealestate.network.NetworkConnectionInterceptor
import com.android.anywhererealestate.repo.CharactersRepository

class AppContainer(context: Context) {
    private val api = CharactersApi.createApi(NetworkConnectionInterceptor(context))
    val charactersRepository = CharactersRepository(api)
}