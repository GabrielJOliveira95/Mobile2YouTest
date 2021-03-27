package com.example.movieslist.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieslist.networking.response.MovieResponse
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

    private var _allMovie = MutableLiveData<List<MovieResponse>>()
    val allMovie: MutableLiveData<List<MovieResponse>>
        get() = _allMovie

    suspend fun getMainMovie() {
        coroutineScope {
            val response = moviesRepository.getMainMovie()

            if (response?.isSuccessful!!) {
                _mainMovie.postValue(response.body())
            } else {
                _error.value = response
            }
        }
    }

    suspend fun getAllMovie() {
        coroutineScope {
            val response = moviesRepository.getAllMovies()

            if (response.isSuccessful){
                _allMovie.postValue(response.body())
            }

        }
    }
}