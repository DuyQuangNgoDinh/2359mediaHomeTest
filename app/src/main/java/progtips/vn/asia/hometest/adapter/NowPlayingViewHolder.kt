package progtips.vn.asia.hometest.adapter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.hometest.R

class NowPlayingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val voteAverage: TextView = itemView.findViewById(R.id.tv_vote_avg)
    private val posterPath:TextView = itemView.findViewById(R.id.tv_poster_path)

    fun bind(item: Movie) {
        voteAverage.text = item.voteAverage.toString()
        posterPath.text = item.posterPath
    }
}