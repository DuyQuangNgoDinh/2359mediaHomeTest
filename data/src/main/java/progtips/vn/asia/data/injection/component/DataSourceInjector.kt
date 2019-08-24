package progtips.vn.asia.data.injection.component

import dagger.Component
import progtips.vn.asia.data.datasource.APIData
import progtips.vn.asia.data.injection.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface DataSourceInjector {
    fun inject(apiData: APIData)
}