package progtips.vn.asia.hometest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.hometest.R

class MovieAdapter: PagedListAdapter<Movie, NowPlayingViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return NowPlayingViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        val movie: Movie? = getItem(position)
        movie?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }
}