package com.example.movieslist.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieslist.data.networking.response.main.movie.MovieResponse
import com.example.movieslist.data.networking.response.similarmovies.Result
import com.example.movieslist.data.repository.MoviesRepository
import kotlinx.coroutines.coroutineScope
import retrofit2.Response

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var _mainMovie = MutableLiveData<MovieResponse>()
    val mainMovie: MutableLiveData<MovieResponse>
        get() = _mainMovie

    private var _error = MutableLiveData<Response<MovieResponse>>()
    val erro: MutableLiveData<Response<MovieResponse>>
        get() = _error

    private var _similarMovies = MutableLiveData<List<Result>>()
    val similarMovies: MutableLiveData<List<Result>>
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
                val response = moviesRepository.getSimilarMovies()
                if (response.isSuccessful) {
                    _similarMovies.postValue(response.body()?.results)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class MovieViewModelFactory(private val moviesRepository: MoviesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(moviesRepository) as T
        }
    }
}