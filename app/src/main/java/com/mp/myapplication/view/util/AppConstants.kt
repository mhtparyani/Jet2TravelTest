package com.mp.myapplication.view.util

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

class AppConstants {

    companion object {
        const val SERVER_VAL = "https://5e99a9b1bc561b0016af3540.mockapi.io/"
        const val url = "jet2/api/v1/blogs"
        const val TIMEOUT = 60 * 1000.toLong()
    }

}