package progtips.vn.asia.domain.entities

import com.squareup.moshi.Json

data class Movie (
    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "vote_average")
    val voteAverage: Double
)