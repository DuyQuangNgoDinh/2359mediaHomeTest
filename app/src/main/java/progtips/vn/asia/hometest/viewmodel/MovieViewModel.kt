package progtips.vn.asia.hometest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import progtips.vn.asia.data.datasource.DataSourceDelegate
import progtips.vn.asia.data.datasource.MovieDataSourceFactory
import progtips.vn.asia.data.repositories.RepositoryImpl
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.usecases.GetNowPlayingUsecase
import androidx.paging.LivePagedListBuilder
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.domain.status.RequestStatus

class MovieViewModel(application: Application): AndroidViewModel(application), DataSourceDelegate<Movie> {
    private val usecase = GetNowPlayingUsecase(RepositoryImpl(application.applicationContext))
    private var subscription: Disposable? = null

    private val movieDataSourceFactory = MovieDataSourceFactory(this)
    val listLiveData: LiveData<PagedList<Movie>>

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        listLiveData = LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)
            .build()
    }

    override fun requestPageData(page: Int, onResult: (List<Movie>) -> Unit) {
        subscription = usecase.execute(page)
            .map {
                val movies = mutableListOf<Movie>()
                for (movie in it.results) {
                    if (!movie.posterPath.isNullOrEmpty())
                        movies.add(movie)
                }
                movies
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrievePostListStart() }
//            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe (
                { result -> onResult(result) },
                { error -> errorMessage.value = error.message }
            )
    }

    fun retry() {
        errorMessage.value = null
        movieDataSourceFactory.getSource()?.retryFailedQuery()
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