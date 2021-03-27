package com.example.movieslist.service

import com.example.movieslist.constants.AppConstants
import com.example.movieslist.networking.response.MovieResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieListService {

   @GET("movie/{movie_id}")
   suspend fun getMainMovie(
      @Path("movie_id") movie_id: Int = 550,
      @Query("api_key") api_key: String = AppConstants.APIKEY
   ): Response<MovieResponse>

   @GET("movie/popular")
   suspend fun getListMovie(
      @Query("api_key") api_key: String = AppConstants.APIKEY
   ): Response<List<MovieResponse>>
}