package com.ehsankolivand.retrofitwithdynamicbaseurl.network

import android.util.Log
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DynamicUrlInterceptor private constructor(): Interceptor {

    lateinit var mScheme: String
    lateinit var mHost: String

    init {
        Log.d("DynamicUrlInterceptor", "init")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()
        //check if mScheme and mHost are initialized

            var newUrl = originalRequest.url.newBuilder()
                .scheme(mScheme)
                .host(mHost)
                .build()
            originalRequest = originalRequest.newBuilder().url(newUrl).build()

        return chain.proceed(originalRequest)
    }

    fun setNewUrl(url: String) {
        Log.d("DynamicUrlInterceptor", "setNewUrl")
        val httpUrl = url.toHttpUrlOrNull()
        mScheme = httpUrl?.scheme ?: "http"
        mHost = httpUrl?.host ?: "localhost"
    }

    companion object {
        private var instance: DynamicUrlInterceptor? = null

        fun getInstance(): DynamicUrlInterceptor {
            if (instance == null) {
                instance = DynamicUrlInterceptor()
            }
            return instance!!
        }
    }
}