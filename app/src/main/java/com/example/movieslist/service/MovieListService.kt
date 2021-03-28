package com.example.movieslist.service

import com.example.movieslist.constants.AppConstants
import com.example.movieslist.networking.response.allmovies.SimilarMoviesResponse
import com.example.movieslist.networking.response.main.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieListService {

   @GET("movie/{movie_id}")
   suspend fun getMainMovie(
      @Path("movie_id") movie_id: Int = AppConstants.MOVIE_ID,
      @Query("api_key") api_key: String = AppConstants.API_KEY
   ): Response<MovieResponse>

   @GET("movie/{movie_id}/similar")
   suspend fun getListMovie(
      @Path("movie_id") movie_id: Int = AppConstants.MOVIE_ID,
      @Query("api_key") api_key: String = AppConstants.API_KEY
   ): Response<SimilarMoviesResponse>
}