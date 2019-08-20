package progtips.vn.asia.hometest.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import progtips.vn.asia.data.repositories.RepositoryImpl
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.status.LoadStatus
import progtips.vn.asia.domain.usecases.GetNowPlayingUsecase
import progtips.vn.asia.hometest.adapter.NowPlayingAdapter

class NowPlayingViewModel: ViewModel() {

    private lateinit var repo: RepositoryImpl
    private var movies: List<Movie> = listOf()
    var adapter = NowPlayingAdapter()

    fun initRepo(resources: Resources) {
        repo = RepositoryImpl(resources)
    }

    fun getData() {
        GetNowPlayingUsecase(repo).execute {
            when (it) {
                is LoadStatus.SUCCESS -> {
                    try {
                        movies = it.response as List<Movie>

                        adapter.updateMovieList(movies)
                    } catch (e: TypeCastException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}