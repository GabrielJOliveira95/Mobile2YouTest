package com.example.movieslist.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslist.R
import com.example.movieslist.constants.AppConstants
import com.example.movieslist.networking.response.allmovies.SimilarMoviesResponse
import com.example.movieslist.networking.response.allmovies.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movies_list.view.*

class AdpterMovie(private val similarMoviesResponse: SimilarMoviesResponse) : RecyclerView.Adapter<AdpterMovie.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: Result) {
            Picasso.get().load(AppConstants.BASEURLPHOTO + result.backdrop_path).into(itemView.movieLogo)
            itemView.movieTitle.text = result.title
            itemView.movieDescription.text = result.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movies_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holderMy: MyViewHolder, position: Int) {
        holderMy.bind(similarMoviesResponse.results[position])
    }

    override fun getItemCount(): Int {
        return similarMoviesResponse.results.size
    }

}