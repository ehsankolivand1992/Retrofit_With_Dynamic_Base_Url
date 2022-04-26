package com.ehsankolivand.retrofitwithdynamicbaseurl.di

import com.ehsankolivand.retrofitwithdynamicbaseurl.network.DynamicUrlInterceptor
import com.ehsankolivand.retrofitwithdynamicbaseurl.network.api.SimpleApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // provide DynamicUrlInterceptor
    @Provides
    @Singleton
    fun provideDynamicUrlInterceptor(): DynamicUrlInterceptor {
        return DynamicUrlInterceptor.getInstance()
    }

    // provide okhttp client
    @Provides
    @Singleton
    fun provideOkHttpClient(dynamicUrlInterceptor: DynamicUrlInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(dynamicUrlInterceptor)
            .build()
    }

    // provide Gson
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    // provide Retrofit Builder
    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl("http://localhost/")//
    }



    //provide SimpleApi
    @Provides
    @Singleton
    fun provideSimpleApi(retrofitBuilder: Retrofit.Builder): SimpleApi {
        return retrofitBuilder.build().create(SimpleApi::class.java)
    }
}