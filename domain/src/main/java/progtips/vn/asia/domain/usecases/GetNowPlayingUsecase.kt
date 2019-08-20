package progtips.vn.asia.domain.usecases

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.domain.entities.Result
import progtips.vn.asia.domain.repositories.Repository
import progtips.vn.asia.domain.status.LoadStatus

class GetNowPlayingUsecase(private val repo: Repository) {

    fun execute(): Single<Result> {
        return repo.getNowPlayingMovies()
    }
}