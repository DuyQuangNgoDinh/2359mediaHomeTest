package progtips.vn.asia.domain.entities

data class Result<T> (
    val page: Int,
    var results: List<T>
)