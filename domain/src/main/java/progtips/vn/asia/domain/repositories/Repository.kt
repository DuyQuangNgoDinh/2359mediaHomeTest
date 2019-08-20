package progtips.vn.asia.domain.repositories

import progtips.vn.asia.domain.entities.Movie
import java.util.*

interface Repository {
    fun getNowPlayingMovies(): List<Movie>
}