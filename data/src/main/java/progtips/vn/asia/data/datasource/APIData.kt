package progtips.vn.asia.data.datasource

import io.reactivex.Single
import progtips.vn.asia.data.BuildConfig
import progtips.vn.asia.data.injection.component.DaggerDataSourceInjector
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.domain.entities.Result

import javax.inject.Inject

class APIData {
    @Inject
    lateinit var restApi: RestApi

    init {
        DaggerDataSourceInjector.builder()
            .build().inject(this)
    }

    fun getNowPlaying(page: Int): Single<Result> = restApi.getNowPlaying(BuildConfig.API_KEY,page)

}