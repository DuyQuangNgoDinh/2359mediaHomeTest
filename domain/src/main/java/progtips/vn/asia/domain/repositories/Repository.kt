package progtips.vn.asia.domain.repositories

import kotlinx.coroutines.flow.Flow
import progtips.vn.asia.domain.entities.Movie

interface Repository {
    suspend fun getNowPlayingMovies(page: Int): Flow<List<Movie>>
}