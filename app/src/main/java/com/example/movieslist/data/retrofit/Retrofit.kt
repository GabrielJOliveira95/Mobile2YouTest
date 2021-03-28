package com.example.movieslist.data.retrofit

import com.example.movieslist.utils.AppConstants
import com.example.movieslist.data.service.MovieListService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val gsonBuilder = GsonBuilder()
    private val client = OkHttpClient.Builder().readTimeout(2, TimeUnit.MINUTES)

    fun getRetrofit(): MovieListService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)


        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build().create(MovieListService::class.java)
    }
}