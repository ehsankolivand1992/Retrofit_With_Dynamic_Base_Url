package com.ehsankolivand.retrofitwithdynamicbaseurl

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ehsankolivand.retrofitwithdynamicbaseurl.model.EmptyModel
import com.ehsankolivand.retrofitwithdynamicbaseurl.network.DynamicUrlInterceptor
import com.ehsankolivand.retrofitwithdynamicbaseurl.network.api.SimpleApi
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dynamicUrlInterceptor: DynamicUrlInterceptor
    @Inject
    lateinit var simpleApi: SimpleApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dynamicUrlInterceptor.setNewUrl("https://api.github.com/")
        var call = simpleApi.get()
        call.enqueue(object: Callback<EmptyModel>
        {
            override fun onResponse(call: Call<EmptyModel>, response: Response<EmptyModel>) {
               Log.d("MainActivitergergergy", "onResponse: ${response.body()}")

            }

            override fun onFailure(call: Call<EmptyModel>, t: Throwable) {
               Log.d("MainActivitergergergy", "onFailure: ${t.message}")
            }

        })
    }
}
