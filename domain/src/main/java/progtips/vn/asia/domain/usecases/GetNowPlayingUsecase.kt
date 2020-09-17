package progtips.vn.asia.domain.usecases

import progtips.vn.asia.domain.repositories.Repository
import javax.inject.Inject

class GetNowPlayingUsecase @Inject constructor(private val repo: Repository) {
    suspend fun execute(page: Int) = repo.getNowPlayingMovies(page)
}