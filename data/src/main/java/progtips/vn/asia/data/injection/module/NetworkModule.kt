package progtips.vn.asia.data.injection.module

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import progtips.vn.asia.data.BuildConfig
import progtips.vn.asia.data.restclient.RestApi
import progtips.vn.asia.data.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val API_KEY_INTERCEPTOR = "API_KEY_INTERCEPTOR"

/**
 * Module which provides all required dependencies about network
 */
@Module
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Singleton
    @Provides
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Singleton
    @Provides
    @JvmStatic
    internal fun provideRetrofitInterface(
        httpClientBuilder: OkHttpClient.Builder,
        @Named(API_KEY_INTERCEPTOR) apiKeyInterceptor: Interceptor
    ): Retrofit {
        httpClientBuilder.addInterceptor(apiKeyInterceptor)

        return Retrofit.Builder()
            .client(httpClientBuilder.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun okhttpBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    println(message)
                }
            })
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)
        }

        return builder
    }

    @Provides
    @Singleton
    @Named(API_KEY_INTERCEPTOR)
    fun provideAPIKeyInterceptor() = object: Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            return chain.proceed(
                chain.request().newBuilder()
                    .url(url)
                    .build()
            )
        }
    }

}