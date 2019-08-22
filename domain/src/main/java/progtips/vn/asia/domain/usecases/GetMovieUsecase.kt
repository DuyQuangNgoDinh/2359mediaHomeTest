package progtips.vn.asia.domain.usecases

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.entities.Result
import progtips.vn.asia.domain.repositories.Repository

class GetMovieUsecase(private val repo: Repository) {

    private var subscription: Disposable? = null

    fun execute(callback: (List<Movie>) -> Unit, page: Int): Single<Result> {
        subscription = repo.getNowPlayingMovies(page)
            .map {
                val movies = mutableListOf<Movie>()
                for (movie in it.results) {
                    if (!movie.posterPath.isNullOrEmpty())
                        movies.add(movie)
                }
                movies
            }
            .subscribe (
                { result -> callback(result)},
                { error -> {}}
            )

        return repo.getNowPlayingMovies(page)
    }

    fun dispose() {
        subscription?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}