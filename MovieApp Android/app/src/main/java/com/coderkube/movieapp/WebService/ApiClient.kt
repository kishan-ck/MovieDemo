package com.coderkube.events.WebService

import com.coderkube.events.WebService.ApiConstant.API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    fun getClient(): ApiInterface {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).addNetworkInterceptor(interceptor)
                .connectTimeout(50, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .readTimeout(50, TimeUnit.MINUTES)
                .writeTimeout(50, TimeUnit.MINUTES)
                .cache(null)
                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiInterface::class.java)

    }
}