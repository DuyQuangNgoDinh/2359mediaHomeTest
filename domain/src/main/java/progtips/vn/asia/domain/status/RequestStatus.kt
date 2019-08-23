package progtips.vn.asia.domain.status

sealed class RequestStatus<out T> {
    data class SUCCESS<T> (val response: T): RequestStatus<T>()
    data class ERROR (val error: Throwable?): RequestStatus<Nothing>()
    object LOADING: RequestStatus<Nothing>()
    object TERMINATE: RequestStatus<Nothing>()
}