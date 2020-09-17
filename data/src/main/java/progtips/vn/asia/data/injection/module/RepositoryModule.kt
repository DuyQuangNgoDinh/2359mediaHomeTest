package progtips.vn.asia.data.injection.module

import dagger.Module
import dagger.Provides
import progtips.vn.asia.data.repositories.RepositoryImpl
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.domain.repositories.Repository

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(api: RestApi):Repository = RepositoryImpl(api)
}