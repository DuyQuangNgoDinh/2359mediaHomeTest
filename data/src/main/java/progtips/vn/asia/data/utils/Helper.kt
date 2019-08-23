package progtips.vn.asia.data.utils

import android.content.Context
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

class Helper private constructor() {
    companion object {
        fun isConnectedToNetwork(context: Context?): Boolean {
            if (context == null) return false

            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnected?:false
        }
    }
}