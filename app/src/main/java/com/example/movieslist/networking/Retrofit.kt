package com.example.movieslist.networking

import com.example.movieslist.constants.AppConstants
import com.example.movieslist.service.MovieListService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.picasso.BuildConfig

class Retrofit {

    companion object {
        private val gsonBuilder = GsonBuilder()
        private val client = OkHttpClient.Builder().readTimeout(2, TimeUnit.MINUTES)

        fun getRetrofit(): MovieListService {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(interceptor)
            }

            return Retrofit.Builder()
                    .baseUrl(AppConstants.BASEURL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build().create(MovieListService::class.java)
        }
    }
}