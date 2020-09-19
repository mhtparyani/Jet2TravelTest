package com.mp.myapplication.view.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mp.myapplication.R
import com.squareup.picasso.Picasso
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

        /**
         * Called when any image is need to be loaded in image view using url
         * @param imageView View
         * @param url url of image
         */
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(imageView: ImageView?, url: String?) {
            if (url != null) {
                if (!url.isEmpty())
                    Picasso.get().load(url).placeholder(R.drawable.ic_baseline_broken_image_24).into(imageView)
            }
        }

        /**
         * Called when any avatar is need to be loaded in image view using url
         * @param imageView View
         * @param url url of image
         */
        @JvmStatic
        @BindingAdapter("app:imageurl")
        fun getImage(imageView: ImageView?, url: String?) {
            if (url != null) {
                if (!url.isEmpty())
                    Picasso.get().load(url).placeholder(R.drawable.ic_baseline_account_circle_24)
                        .into(imageView)
            }
        }
    }

}