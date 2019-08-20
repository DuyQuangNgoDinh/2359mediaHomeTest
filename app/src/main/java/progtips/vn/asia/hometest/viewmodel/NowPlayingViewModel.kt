package progtips.vn.asia.hometest.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.data.repositories.RepositoryImpl
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.entities.Result
import progtips.vn.asia.domain.usecases.GetNowPlayingUsecase
import progtips.vn.asia.hometest.adapter.NowPlayingAdapter

class NowPlayingViewModel: ViewModel() {
    private var subscription: Disposable? = null
    private var repo: RepositoryImpl = RepositoryImpl()
    private var movies: List<Movie> = listOf()
    var adapter = NowPlayingAdapter()

    init {
        subscription = GetNowPlayingUsecase(repo).execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate({})
            .subscribe(
                { result -> doOnSuccess(result) },
                { error -> {} }
            )
    }

    override fun onCleared() {
        subscription?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }

        super.onCleared()
    }

    private fun doOnSuccess(result: Result) {
        try {
            movies = result.results
            adapter.updateMovieList(movies)
        } catch (e: TypeCastException) {
            e.printStackTrace()
        }
    }
}