package progtips.vn.asia.data.restclient

import io.reactivex.Single
import progtips.vn.asia.domain.entities.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    /**
     * Get the list of books from API
     */
    @GET("now_playing")
    fun getNowPlaying(@Query("api_key") key: String,
                      @Query("page") page: Int): Single<Result>

    /**
     * Get the list of books from API
     */
    @GET("now_playing")
    fun getNowPlayingMovies(@Query("api_key") key: String,
                      @Query("page") page: Int): Result
}