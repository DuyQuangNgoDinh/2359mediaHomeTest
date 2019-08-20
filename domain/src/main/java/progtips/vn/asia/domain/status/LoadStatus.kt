package progtips.vn.asia.domain.status

sealed class LoadStatus {
    data class SUCCESS (val response: Any): LoadStatus()
    data class ERROR (val error: Throwable?): LoadStatus()
    object LOADING: LoadStatus()
    object TERMINATE: LoadStatus()
}