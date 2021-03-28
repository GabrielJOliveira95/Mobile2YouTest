package com.example.movieslist.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslist.R
import com.example.movieslist.data.networking.response.similarmovies.Result
import com.example.movieslist.utils.AppConstants
import com.example.movieslist.utils.ToGenres
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movies_list.view.*

class AdpterMovie(private val similarMoviesResponse: List<Result>) :
    RecyclerView.Adapter<AdpterMovie.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: Result) {
            Picasso.get().load(AppConstants.BASE_URL_PHOTO + result.backdrop_path)
                .into(itemView.movieLogo)
            itemView.movieTitle.text = result.title
            itemView.movieDescription.text = getGenre(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_movies_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holderMy: MyViewHolder, position: Int) {
        holderMy.bind(similarMoviesResponse[position])
    }

    override fun getItemCount(): Int {
        return similarMoviesResponse.size
    }
}

private fun getGenre(similarMovie: Result): String {
    val genreIds = similarMovie.genre_ids
    val year = similarMovie.release_date.take(4)
    var genres = ""
    genreIds.forEach {
        genres += "${ToGenres.invoke(it)}, "
    }
    return "$year ${genres.subSequence(0, genres.count() - 2)}"
}
