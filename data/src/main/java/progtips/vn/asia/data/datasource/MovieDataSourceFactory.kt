package progtips.vn.asia.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import progtips.vn.asia.domain.entities.Movie

class MovieDataSourceFactory(private val dataSourceDelegate: DataSourceDelegate<Movie>): DataSource.Factory<Int, Movie>() {
    private val sourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val latestSource = MovieDataSource(dataSourceDelegate)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }

    fun getSource() = sourceLiveData.value
}