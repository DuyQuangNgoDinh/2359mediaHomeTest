package progtips.vn.asia.hometest.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.hometest.R
import com.bumptech.glide.Glide
import progtips.vn.asia.data.utils.IMG_URL


class NowPlayingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val voteAverage: TextView = itemView.findViewById(R.id.tv_vote_avg)
    private val imgPoster: ImageView = itemView.findViewById(R.id.img_poster)

    fun bind(item: Movie) {
        voteAverage.text = item.voteAverage.toString()
        Glide
            .with(itemView.context)
            .load(IMG_URL + item.posterPath)
            .centerCrop()
            .placeholder(R.drawable.poster_placeholder)
            .into(imgPoster)
    }
}