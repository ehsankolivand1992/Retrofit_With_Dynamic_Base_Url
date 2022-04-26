package com.ehsankolivand.retrofitwithdynamicbaseurl.network.api

import com.ehsankolivand.retrofitwithdynamicbaseurl.model.EmptyModel
import retrofit2.Call
import retrofit2.http.GET



interface SimpleApi {

    @GET("/")
    fun get():Call<EmptyModel>
}