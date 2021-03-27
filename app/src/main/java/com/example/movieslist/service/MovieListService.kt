package com.example.movieslist.service

import com.example.movieslist.constants.AppConstants
import com.example.movieslist.networking.response.MovieResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieListService {

   @GET("")
   suspend fun getMainMovie(
      @Query("api_key") api_key: String = AppConstants.APIKEY,
      @Path("movie_id") movie_id: Int = 2,
   ): Response<MovieResponse>
}