package progtips.vn.asia.domain.entities

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int = -1,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double
)