package com.example.movieslist.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieslist.R
import com.example.movieslist.activity.viewmodel.MovieViewModel
import com.example.movieslist.adpter.AdpterMovie
import com.example.movieslist.constants.AppConstants
import com.example.movieslist.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adpterMovie: AdpterMovie
    private lateinit var binding: ActivityMainBinding
    private val scope = CoroutineScope(newSingleThreadContext("scope"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewRoot = binding.root
        setContentView(viewRoot)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        initScope()

    }

    override fun onResume() {
        super.onResume()
        initObservables()
    }

    private fun initScope() {
        scope.launch {
            viewModel.getMainMovie()
            viewModel.getAllMovie()
        }
    }

    private fun initObservables() {
        viewModel.mainMovie.observe(this, {
            if (it != null) {
                binding.mainMovieTitle.text = it.title
                binding.likesMainMovieTv.text = "${it.vote_count} ${getString(R.string.likes)}"
                binding.popularutyTv.text = "${it.popularity} ${getString(R.string.views)}"
                Picasso.get().load(AppConstants.BASEURLPHOTO + it.backdrop_path)
                    .into(binding.mainMovieLogo)
            }
        })

        viewModel.allMovie.observe(this, {
            adpterMovie = AdpterMovie(it)
            configRecyclerView(adpterMovie)
        })

        viewModel.erro.observe(this, {
            Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
        })
    }

    private fun configRecyclerView(adapter: AdpterMovie) {
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.HORIZONTAL
            )
        )

    }


}