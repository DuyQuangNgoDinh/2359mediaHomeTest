package progtips.vn.asia.data.datasource

interface DataSourceDelegate<T> {
    fun requestPageData(page: Int, onResult: (List<T>) -> Unit)
}