package progtips.vn.asia.hometest.injector

import dagger.Component
import progtips.vn.asia.data.injection.module.NetworkModule
import progtips.vn.asia.data.injection.module.RepositoryModule
import progtips.vn.asia.hometest.viewmodel.MovieViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface ViewModelInjector {
    fun inject(postListViewModel: MovieViewModel)
}
