package com.example.moviedb.data

import com.example.moviedb.network.ITMDBApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit

class DefaultAppContainer: IAppContainer {
    private val tmdbBaseUrl = "https://api.themoviedb.org/3/"
    private val tmdbApiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZTllZDc0ZjhlYzExMTIwNDJkNTNkNzg0OTg3YzY0YiIsIm5iZiI6MTU4NTUxNTM3Ny4yOTQwMDAxLCJzdWIiOiI1ZTgxMGI3MTZkOTdlNjAwMTRjNmY2YWUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.qscybTuhWaj9F6EB-nuC-LTbi4-qrdh8ahnV4OOJ_pU"

    private fun createAuthorizedOkHttpClient(apiKey: String): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val requestWithAuth: Request = originalRequest.newBuilder()
                .header("Authorization", "Bearer $apiKey")
                .build()
            chain.proceed(requestWithAuth)
        }
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(tmdbBaseUrl)
        .client(createAuthorizedOkHttpClient(apiKey = tmdbApiKey))
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: ITMDBApiService by lazy {
        retrofit.create(ITMDBApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val movieRepository: IMovieRepository by lazy {
        NetworkMovieRepository(retrofitService)
    }
}