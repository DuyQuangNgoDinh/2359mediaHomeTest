package progtips.vn.asia.data.repositories

import android.content.Context
import io.reactivex.Single
import progtips.vn.asia.data.R
import progtips.vn.asia.data.datasource.APIData
import progtips.vn.asia.data.utils.Helper
import progtips.vn.asia.domain.entities.Result
import progtips.vn.asia.domain.repositories.Repository

class RepositoryImpl(private val context: Context): Repository {
    override fun getNowPlayingMovies(page: Int): Single<Result> {
        if (Helper.isConnectedToNetwork(context))
            return APIData().getNowPlaying(page)

        val errorMessage:String = context.getString(R.string.error_no_internet_connection)
        return Single.error(Throwable(errorMessage))
    }

}