package com.mp.myapplication.view.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import java.util.*

class CustomClass {
    companion object{
        fun getInstance(): GsonBuilder{
            val ser: JsonSerializer<Date> =
                JsonSerializer { src, typeOfSrc, context ->
                    (if (src == null) null else JsonPrimitive(
                        src.time
                    ))!!
                }

            val deser: JsonDeserializer<Date> =
                JsonDeserializer { json, typeOfT, context ->
                    (if (json == null) null else Date(json.asLong))!!
                }

            return GsonBuilder()
                .registerTypeAdapter(Date::class.java, ser)
                .registerTypeAdapter(Date::class.java, deser)
        }
    }
}