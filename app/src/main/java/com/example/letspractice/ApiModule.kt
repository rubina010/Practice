package com.example.letspractice

import com.example.letspractice.networking.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule {
    val BASE_URL = "https://api.stackexchange.com/2.2/"

    companion object {
        fun newInstance(): ApiModule {
            return ApiModule()
        }
    }

    fun createApiService(): ApiService {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
    }
}