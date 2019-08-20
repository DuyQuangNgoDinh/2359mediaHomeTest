package progtips.vn.asia.domain.entities

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double
)