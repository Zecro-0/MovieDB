package com.example.moviedb.data

import com.example.moviedb.network.ITMDBApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class DefaultAppContainer(
    private val db: AppDatabase
): IAppContainer {

    private val tmdbBaseUrl = "https://api.themoviedb.org/3/"
    private val tmdbApiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkODY3ZjQxMzJkZjc5MDhjZGJjZTIzYmI4MTQyOTViOCIsIm5iZiI6MS41ODU1MTUzNzcyOTQwMDAxZSs5LCJzdWIiOiI1ZTgxMGI3MTZkOTdlNjAwMTRjNmY2YWUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Vu78L2DzR3-w_4_N22RB50Nxn1ynXk4asQfWYSXZuNU"

    private fun createAuthorizedOkHttpClient(apiKey: String): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val requestWithAuth: Request = originalRequest.newBuilder()
                .header("Authorization", "Bearer $apiKey")
                .build()
            chain.proceed(requestWithAuth)
        }
        return OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS)) // After 30 seconds throw away the connection
            .addInterceptor(authInterceptor)
            .build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(tmdbBaseUrl.toString())
        .client(createAuthorizedOkHttpClient(apiKey = tmdbApiKey.toString()))
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
        NetworkMovieRepository(retrofitService, db)
    }
}