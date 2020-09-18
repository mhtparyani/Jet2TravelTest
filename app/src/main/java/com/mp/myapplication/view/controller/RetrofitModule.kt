package com.mp.myapplication.view.controller

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mp.myapplication.view.util.AppConstants
import com.mp.myapplication.view.util.AppConstants.Companion.TIMEOUT
import com.mp.myapplication.view.util.CustomGsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitModule {


    companion object {
        @JvmStatic
        fun providesOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            val clientBuilder = OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS).writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
            return clientBuilder.build()
        }

        @JvmStatic
        fun providesRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.SERVER_VAL)
            .addConverterFactory(GsonConverterFactory.create(CustomGsonBuilder.getInstance().create()))
            .client(providesOkHttpClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    }
}