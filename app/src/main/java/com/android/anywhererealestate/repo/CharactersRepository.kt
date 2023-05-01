package com.android.anywhererealestate.repo

import com.android.anywhererealestate.model.ApiResponse
import com.android.anywhererealestate.network.CharactersApi
import com.android.anywhererealestate.util.JSON_FORMAT
import retrofit2.Response

class CharactersRepository(private val api: CharactersApi) : Repository {
    override suspend fun getCharactersFromNetwork(query: String): Response<ApiResponse> {
//        return api.getCharacters(query, JSON_FORMAT)
        return api.getCharacters()
    }

}