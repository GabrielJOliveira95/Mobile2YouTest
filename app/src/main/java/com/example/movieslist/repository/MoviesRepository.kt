package com.example.movieslist.repository

import com.example.movieslist.networking.response.allmovies.SimilarMoviesResponse
import com.example.movieslist.networking.retrofit.Retrofit
import com.example.movieslist.networking.response.main.movie.MovieResponse
import retrofit2.Response

class MoviesRepository() {
    private val retrofit = Retrofit()

    suspend fun getMainMovie(): Response<MovieResponse> {
        val response = retrofit.getRetrofit().getMainMovie()
        try {
            if (response.isSuccessful) return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

    suspend fun getAllMovies(): Response<SimilarMoviesResponse> {
        val response = retrofit.getRetrofit().getListMovie()
        try {
            if (response.isSuccessful) return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}