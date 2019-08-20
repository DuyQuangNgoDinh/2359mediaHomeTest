package progtips.vn.asia.data.datasource

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import progtips.vn.asia.data.R
import progtips.vn.asia.domain.entities.Movie
import progtips.vn.asia.domain.entities.Result
import java.io.BufferedReader
import java.util.ArrayList

class RawData {
    fun getRawData(resources: Resources): Result {
        val inputStream = resources.openRawResource(R.raw.now_loading_movies)
        val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
        val resultType = object : TypeToken<Result>() {}.type
        return Gson().fromJson(jsonString, resultType)
    }
}