package progtips.vn.asia.data.datasource

import androidx.paging.PageKeyedDataSource
import progtips.vn.asia.domain.entities.Movie

class MovieDataSource(private val dataSourceDelegate: DataSourceDelegate<Movie>): PageKeyedDataSource<Int, Movie>() {

    // FOR DATA ---
    private var retryQuery: (() -> Any)? = null // Keep reference of the last query (to be able to retry it if necessary)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        retryQuery = { loadInitial(params, callback) }

        dataSourceDelegate.requestPageData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        retryQuery = { loadAfter(params, callback) }

        dataSourceDelegate.requestPageData(params.key) {
            callback.onResult(it, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

    fun retryFailedQuery() {
        val prevQuery = retryQuery
        retryQuery = null
        prevQuery?.invoke()
    }
}