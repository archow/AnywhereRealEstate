package com.android.anywhererealestate.network

import com.android.anywhererealestate.BuildConfig
import com.android.anywhererealestate.model.ApiResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

//    @GET("")
//    suspend fun getCharacters(
//        @Query("q") query: String,
//        @Query("format") format: String
//    ) : Response<ApiResponse>

    @GET("?q=the+wire+characters&format=json")
    suspend fun getCharacters() : Response<ApiResponse>

    companion object {
        private var retrofit: Retrofit? = null

        fun createApi(networkConnectionInterceptor: NetworkConnectionInterceptor): CharactersApi {
            return getRetrofit(networkConnectionInterceptor).create(CharactersApi::class.java)
        }

        fun getLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        fun getClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(networkConnectionInterceptor).build()
        }

        fun getGsonConverterFactory(): Converter.Factory {
            return GsonConverterFactory.create()
        }

        private fun getRetrofit(networkConnectionInterceptor: NetworkConnectionInterceptor): Retrofit {
            return retrofit ?: Retrofit.Builder()
                .client(getClient(networkConnectionInterceptor))
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(getGsonConverterFactory())
                .build()
        }
    }
}