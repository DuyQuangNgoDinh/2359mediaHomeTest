package progtips.vn.asia.hometest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.hometest.R

class NowPlayingAdapter: RecyclerView.Adapter<NowPlayingViewHolder>() {
    var movieList:List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return NowPlayingViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        if (position < movieList.size)
            holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun updateMovieList(list: List<Movie>) {
        this.movieList = list
        notifyDataSetChanged()
    }
}