package com.example.movieslist.data.repository

import com.example.movieslist.data.networking.response.genre.GenreResponse
import com.example.movieslist.data.networking.response.similarmovies.SimilarMoviesResponse
import com.example.movieslist.data.retrofit.Retrofit
import com.example.movieslist.data.networking.response.main.movie.MovieResponse
import retrofit2.Response

class MoviesRepository {
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

    suspend fun getSimilarMovies(): Response<SimilarMoviesResponse> {
        val response = retrofit.getRetrofit().getListMovie()
        try {
            if (response.isSuccessful) return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

    suspend fun getMovieGenre(): Response<GenreResponse> {
        val response = retrofit.getRetrofit().getIdGenre()
        try {
            if (response.isSuccessful) return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}