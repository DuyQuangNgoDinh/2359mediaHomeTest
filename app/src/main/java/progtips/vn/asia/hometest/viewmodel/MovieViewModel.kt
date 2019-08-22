package progtips.vn.asia.hometest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.data.datasource.DataSourceDelegate
import progtips.vn.asia.data.datasource.MovieDataSourceFactory
import progtips.vn.asia.data.repositories.RepositoryImpl
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.usecases.GetNowPlayingUsecase
import androidx.paging.LivePagedListBuilder
import progtips.vn.asia.domain.usecases.GetMovieUsecase

class MovieViewModel: ViewModel(), DataSourceDelegate<Movie> {
    private var subscription: Disposable? = null
    private var repo: RepositoryImpl = RepositoryImpl()

    private var movieDataSourceFactory = MovieDataSourceFactory(this)
    var listLiveData: LiveData<PagedList<Movie>>

    val progressVisibility:MutableLiveData<Boolean> = MutableLiveData()

    init {

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        listLiveData = LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)
            .build()

        progressVisibility.value = false
    }

    override fun requestPageData(page: Int, onResult: (List<Movie>) -> Unit) {
//        progressVisibility.value = true

//        subscription = GetNowPlayingUsecase(repo).execute(page)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result -> onResult(result.results) },
//                { error -> progressVisibility.value = false }
//            )

        GetMovieUsecase(repo).execute(onResult, page)
    }

    override fun onCleared() {
        subscription?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }

        super.onCleared()
    }
}