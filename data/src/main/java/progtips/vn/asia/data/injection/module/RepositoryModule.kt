package progtips.vn.asia.data.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import progtips.vn.asia.data.repositories.RepositoryImpl
import progtips.vn.asia.domain.repositories.Repository
import progtips.vn.asia.domain.usecases.GetNowPlayingUsecase

@Module
class RepositoryModule(val context: Context) {
    @Provides
    fun provideRepository():Repository = RepositoryImpl(context)

    @Provides
    fun provideGetNowPlayingUsecase(repository: Repository) = GetNowPlayingUsecase(repository)
}