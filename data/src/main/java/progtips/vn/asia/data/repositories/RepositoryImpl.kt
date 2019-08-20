package progtips.vn.asia.data.repositories

import android.content.res.Resources
import progtips.vn.asia.data.datasource.RawData
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.repositories.Repository

class RepositoryImpl(private val resources: Resources): Repository {
    override fun getNowPlayingMovies(): List<Movie> {
        return RawData().getRawData(resources).results
    }
}