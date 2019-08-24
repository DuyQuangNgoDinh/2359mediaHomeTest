package progtips.vn.asia.data.datasource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import progtips.vn.asia.data.injection.component.DaggerDataSourceInjector
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.data.utils.API_KEY
import progtips.vn.asia.data.utils.BASE_URL
import progtips.vn.asia.domain.entities.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class APIData {
    @Inject
    lateinit var restApi: RestApi

    init {
        DaggerDataSourceInjector.builder()
            .build().inject(this)
    }

    fun getNowPlaying(page: Int): Single<Result> = restApi.getNowPlaying(API_KEY,page)

}