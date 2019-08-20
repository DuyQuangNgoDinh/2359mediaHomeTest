package progtips.vn.asia.domain.repositories

import io.reactivex.Single
import progtips.vn.asia.domain.entities.Result

interface Repository {
    fun getNowPlayingMovies(): Single<Result>
}