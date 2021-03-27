package com.example.movieslist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieslist.R
import com.example.movieslist.activity.viewmodel.MovieViewModel
import com.example.movieslist.adpter.AdpterMovie
import com.example.movieslist.networking.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MovieViewModel
    lateinit var adpterMovie: AdpterMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =  ViewModelProvider(this).get(MovieViewModel::class.java)

        initObservables()

    }

    fun initObservables(){
        viewModel.mainMovie.observe(this, {
            adpterMovie = AdpterMovie(it)
        })
    }


}