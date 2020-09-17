package progtips.vn.asia.data.repositories

import android.content.Context
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.domain.repositories.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: RestApi
): Repository {
//    override fun getNowPlayingMovies(page: Int): Single<Result> {
//        if (Helper.isConnectedToNetwork(context))
//            return APIData().getNowPlaying(page)
//
//        val errorMessage:String = context.getString(R.string.error_no_internet_connection)
//        return Single.error(Throwable(errorMessage))
//    }

    @InternalCoroutinesApi
    override suspend fun getNowPlayingMovies(page: Int) = flow {
        val nowPlaying = api.getNowPlaying(page)
        emit(nowPlaying.results)
    }
}