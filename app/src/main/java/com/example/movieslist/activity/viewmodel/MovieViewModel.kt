package com.example.movieslist.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieslist.networking.response.MovieResponse
import com.example.movieslist.repository.MoviesRepository
import kotlinx.coroutines.coroutineScope
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class MovieViewModel @Inject constructor(private val moviesRepository: MoviesRepository): ViewModel() {

    private var _mainMovie = MutableLiveData<MovieResponse>()
    val mainMovie: MutableLiveData<MovieResponse>
        get() = _mainMovie

    private var _error = MutableLiveData<Response<MovieResponse>>()
    val erro: MutableLiveData<Response<MovieResponse>>
        get() = _error


    suspend fun getMainMovie(){
        coroutineScope {
            val response = moviesRepository.getMainMovie()

            if (response.isSuccessful){
                mainMovie.value = response.body()
            } else {
               _error.value = response
            }
        }
    }
}