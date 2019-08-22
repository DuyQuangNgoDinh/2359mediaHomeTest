package progtips.vn.asia.data.datasource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.data.utils.API_KEY
import progtips.vn.asia.data.utils.BASE_URL
import progtips.vn.asia.domain.entities.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class APIData {
//    @Inject
    var restApi: RestApi = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    .build().create(RestApi::class.java)

    fun getNowLoading(page: Int): Single<Result> = restApi.getNowPlaying(API_KEY,page)

    fun getNowPlaying(page: Int): Result = restApi.getNowPlayingMovies(API_KEY,page)
}