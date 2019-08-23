package progtips.vn.asia.domain.usecases

import progtips.vn.asia.domain.repositories.Repository

class GetNowPlayingUsecase(private val repo: Repository) {
    fun execute(page: Int) = repo.getNowPlayingMovies(page)
}