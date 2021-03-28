package com.example.movieslist.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieslist.networking.response.allmovies.SimilarMoviesResponse
import com.example.movieslist.networking.response.main.movie.MovieResponse
import com.example.movieslist.repository.MoviesRepository
import kotlinx.coroutines.coroutineScope
import retrofit2.Response

class MovieViewModel() : ViewModel() {
    var moviesRepository = MoviesRepository()

    private var _mainMovie = MutableLiveData<MovieResponse>()
    val mainMovie: MutableLiveData<MovieResponse>
        get() = _mainMovie

    private var _error = MutableLiveData<Response<MovieResponse>>()
    val erro: MutableLiveData<Response<MovieResponse>>
        get() = _error

    private var _similarMovies = MutableLiveData<SimilarMoviesResponse>()
    val similarMovies: MutableLiveData<SimilarMoviesResponse>
        get() = _similarMovies

    suspend fun getMainMovie() {
        coroutineScope {
            try {
                val response = moviesRepository.getMainMovie()
                if (response.isSuccessful) {
                    _mainMovie.postValue(response.body())
                } else {
                    _error.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getSimilarMovies() {
        coroutineScope {
            try {
                val response = moviesRepository.getAllMovies()
                if (response.isSuccessful) {
                    _similarMovies.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}