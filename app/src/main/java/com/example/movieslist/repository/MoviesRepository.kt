package com.example.movieslist.repository

import com.example.movieslist.networking.response.MovieResponse
import com.example.movieslist.service.MovieListService
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieListService: MovieListService) {

    suspend fun getMainMovie(): Response<MovieResponse> {
        val response = movieListService.getMainMovie()
        try {
            if (response.isSuccessful) return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}