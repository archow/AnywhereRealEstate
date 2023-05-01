package com.android.anywhererealestate.repo

import com.android.anywhererealestate.model.ApiResponse
import retrofit2.Response

interface Repository {
    suspend fun getCharactersFromNetwork(query: String): Response<ApiResponse>
}