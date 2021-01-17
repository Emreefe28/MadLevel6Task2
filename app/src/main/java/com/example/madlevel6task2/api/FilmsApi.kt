package com.example.madlevel6task2.api

import com.example.madlevel6task2.service.FilmApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FilmsApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://api.themoviedb.org/3/discover/movie/"

        /**
         * @return [FilmApiService] The service class off the retrofit client.
         */
        fun createApi(): FilmApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor { addApiKeyInterceptor(it) }
                .build()

            // Create the Retrofit instance
            val triviaApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit TriviaApiService
            return triviaApi.create(FilmApiService::class.java)
        }

        private fun addApiKeyInterceptor(it: Interceptor.Chain): Response {
            val originalRequest = it.request()
            val originalHttpUrl = originalRequest.url

            val urlWithApiKey = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "438c4601369277c3b6b65ba9e92f1393")
                .build()

            val requestWithApiKey = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()

            return it.proceed(request = requestWithApiKey)
        }
    }
}
