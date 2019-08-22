package progtips.vn.asia.data.datasource

import androidx.paging.PageKeyedDataSource
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.data.utils.API_KEY
import progtips.vn.asia.data.utils.BASE_URL
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.entities.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieDataSource(private val dataSourceDelegate: DataSourceDelegate<Movie>): PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        dataSourceDelegate.requestPageData(
            1) {
            callback.onResult(it, 0, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        dataSourceDelegate.requestPageData(
            params.key
        ) {
            callback.onResult(it, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}