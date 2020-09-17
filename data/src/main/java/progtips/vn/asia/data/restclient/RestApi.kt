package progtips.vn.asia.data.restclient

import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.entities.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    /**
     * Get the list of books from API
     */
    @GET("now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): Result<Movie>
}