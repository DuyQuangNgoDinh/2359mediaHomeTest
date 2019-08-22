package progtips.vn.asia.hometest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import progtips.vn.asia.hometest.R
import progtips.vn.asia.hometest.adapter.MovieAdapter
import progtips.vn.asia.hometest.adapter.MovieGridItemDecoration
import progtips.vn.asia.hometest.viewmodel.MovieViewModel

/**
 * Fragment for displaying now playing movies
 *
 */
class NowPlayingFragment : Fragment() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var recyclerView:RecyclerView
    private lateinit var progressBar: ProgressBar
    //private var adapter = NowPlayingAdapter()
    private var adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_now_playing, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_now_playing)

        recyclerView = view.findViewById(R.id.now_playing_recycler)
        progressBar = view.findViewById(R.id.progress_now_playing)

        return view
    }

    override fun onStart() {
        recyclerView.layoutManager = GridAutofitLayoutManager(context!!, -1)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.progressVisibility.observe(this, Observer {
            viewModel.progressVisibility.value?.let {
                when(it) {
                    true -> progressBar.visibility = View.VISIBLE
                    false -> progressBar.visibility = View.GONE
                }
            }
        })

        recyclerView.adapter = adapter

        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_movie_grid_spacing_small)
        recyclerView.addItemDecoration(MovieGridItemDecoration(smallPadding))

        //viewModel.movies.observe(this, Observer { adapter.updateMovieList(it) })
        viewModel.listLiveData.observe(this, Observer { adapter.submitList(it) })
        super.onStart()
    }

}
