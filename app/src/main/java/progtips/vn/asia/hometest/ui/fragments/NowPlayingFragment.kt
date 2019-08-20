package progtips.vn.asia.hometest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import progtips.vn.asia.hometest.R
import progtips.vn.asia.hometest.viewmodel.NowPlayingViewModel

/**
 * Fragment for displaying now playing movies
 *
 */
class NowPlayingFragment : Fragment() {
    private lateinit var viewModel: NowPlayingViewModel
    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_now_playing, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_now_playing)

        recyclerView = view.findViewById(R.id.now_playing_recycler)


        return view
    }

    override fun onStart() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel = ViewModelProviders.of(this).get(NowPlayingViewModel::class.java)

        recyclerView.adapter = viewModel.adapter
        super.onStart()
    }

}
