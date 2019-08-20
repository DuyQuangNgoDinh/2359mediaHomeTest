package progtips.vn.asia.data.repositories

import io.reactivex.Single
import progtips.vn.asia.data.datasource.APIData
import progtips.vn.asia.domain.entities.Result
import progtips.vn.asia.domain.repositories.Repository

class RepositoryImpl: Repository {

    override fun getNowPlayingMovies(): Single<Result> {
        return APIData().getNowLoading()
    }
}