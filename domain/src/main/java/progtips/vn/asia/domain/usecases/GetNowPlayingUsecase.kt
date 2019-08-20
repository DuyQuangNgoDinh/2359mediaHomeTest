package progtips.vn.asia.domain.usecases

import progtips.vn.asia.domain.repositories.Repository
import progtips.vn.asia.domain.status.LoadStatus

class GetNowPlayingUsecase(private val repo: Repository) {

    fun execute(callback: (LoadStatus) -> Unit) {
        callback(LoadStatus.SUCCESS(repo.getNowPlayingMovies()))
    }
}