package com.example.movieslist.networking.response.allmovies

data class AllMoviesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)