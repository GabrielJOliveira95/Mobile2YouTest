package com.example.movieslist.ui.movie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieslist.R
import com.example.movieslist.data.repository.MoviesRepository
import com.example.movieslist.databinding.ActivityMainBinding
import com.example.movieslist.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adpterMovie: AdpterMovie
    private lateinit var binding: ActivityMainBinding
    private val factory = MovieViewModel.MovieViewModelFactory(MoviesRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewRoot = binding.root
        setContentView(viewRoot)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initObservers()
    }

    private fun initView() {
        lifecycleScope.launch {
            viewModel.getMainMovie()
            viewModel.getSimilarMovies()
        }
    }

    private fun initObservers() {
        viewModel.mainMovie.observe(this, {
            if (it != null) {
                binding.mainMovieTitle.text = it.title
                binding.likesMainMovieTv.text = "${it.vote_count} ${getString(R.string.likes)}"
                binding.popularutyTv.text = "${it.popularity} ${getString(R.string.views)}"
                Picasso.get().load(AppConstants.BASE_URL_PHOTO + it.backdrop_path)
                    .into(binding.mainMovieLogo)
            }
        })
        viewModel.similarMovies.observe(this, {
            adpterMovie = AdpterMovie(it)
            configRecyclerView(adpterMovie)
        })
        viewModel.erro.observe(this, {
            Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
        })
        viewModel.netWorkError.observe(this, {
            Toast.makeText(applicationContext, getString(R.string.error_conexao), Toast.LENGTH_LONG).show()
        })
    }

    private fun configRecyclerView(_adapter: AdpterMovie) {
        binding.recyclerView.apply {
            hasFixedSize()
            adapter = _adapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

}