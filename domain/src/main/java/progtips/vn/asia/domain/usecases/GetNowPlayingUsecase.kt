package progtips.vn.asia.domain.usecases

import io.reactivex.Single
import progtips.vn.asia.domain.entities.Result
import progtips.vn.asia.domain.repositories.Repository

class GetNowPlayingUsecase(private val repo: Repository) {

    fun execute(page: Int): Single<Result> {
        return repo.getNowPlayingMovies(page)
    }
}