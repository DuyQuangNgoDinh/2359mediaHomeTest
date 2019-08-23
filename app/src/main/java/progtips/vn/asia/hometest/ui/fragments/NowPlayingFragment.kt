package progtips.vn.asia.hometest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
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
    private lateinit var containerEmpty: LinearLayout
    private lateinit var errorTextview: TextView
    private lateinit var retryButton: MaterialButton
    private var errorSnackbar: Snackbar? = null
    private var errorType = ERROR_TYPE.EMPTY_SCREEN

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
        containerEmpty = view.findViewById(R.id.layout_empty)
        errorTextview = view.findViewById(R.id.tv_error_message)
        retryButton = view.findViewById(R.id.btn_retry)
        retryButton.setOnClickListener { viewModel.retry() }

        viewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)

        viewModel.listLiveData.observe(this, Observer { adapter.submitList(it) })
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if (errorMessage != null) showError(view, errorMessage) else hideError()
        })
        viewModel.loadingVisibility.observe(this, Observer {
            when(it) {
                true -> progressBar.visibility = View.VISIBLE
                false -> progressBar.visibility = View.GONE
            }
        })

        return view
    }

    override fun onStart() {
        recyclerView.layoutManager = GridAutofitLayoutManager(context!!, -1)
        recyclerView.adapter = adapter

        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_movie_grid_spacing_small)
        recyclerView.addItemDecoration(MovieGridItemDecoration(smallPadding))

        super.onStart()
    }

    private fun showError(view: View, errorMessage: String) {
        if (viewModel.isDataEmpty()) {
            containerEmpty.visibility = View.VISIBLE
            errorTextview.text = errorMessage
            errorType = ERROR_TYPE.EMPTY_SCREEN
        }
        else {
            errorSnackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(R.string.retry) { viewModel.retry() }
            errorSnackbar?.show()
            errorType = ERROR_TYPE.SNACKBAR
        }
    }

    private fun hideError() {
        when (errorType) {
            ERROR_TYPE.SNACKBAR -> errorSnackbar?.dismiss()
            ERROR_TYPE.EMPTY_SCREEN -> containerEmpty.visibility = View.GONE
        }
    }

    enum class ERROR_TYPE {
        EMPTY_SCREEN,
        SNACKBAR
    }
}
