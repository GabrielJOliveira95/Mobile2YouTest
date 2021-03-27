package com.example.movieslist.repository

import com.example.movieslist.networking.retrofit.Retrofit
import com.example.movieslist.networking.response.MovieResponse
import retrofit2.Response

class MoviesRepository () {

    suspend fun getMainMovie(): Response<MovieResponse> {
        val retrofit = Retrofit()
        val response = retrofit.getRetrofit().getMainMovie()
        try {
            if (response.isSuccessful) return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}