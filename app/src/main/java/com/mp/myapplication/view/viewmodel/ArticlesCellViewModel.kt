package com.mp.myapplication.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mp.myapplication.view.model.DataModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ArticlesCellViewModel: ViewModel() {

    lateinit var dataModel: DataModel
    lateinit var userName:String
    lateinit var userDesignation:String
    lateinit var article_content:String
    lateinit var article_title:String
    lateinit var article_url:String
    var likes:String? = null
    var comments:String? = null
    lateinit var time:String
    lateinit var user_avatar:String
    lateinit var article_image:String
    var isArticleImageAvailable:Boolean = false

    fun bind(dataModel: DataModel){
        this.dataModel=dataModel
        time="1 min"
        article_image=""
        article_title=""
        article_url=""
        if (dataModel.media.size>0){
            isArticleImageAvailable=true
            val imageModel=dataModel.media.get(0)
            article_image= imageModel.image
            article_title= imageModel.title
            article_url=imageModel.url
        }else{
            isArticleImageAvailable=false
        }
        if (dataModel.user.size>0){
            val userModel= dataModel.user.get(0)
            userName= userModel.name+" "+userModel.lastname
            user_avatar= userModel.avatar
            userDesignation=userModel.designation
        }
        article_content=dataModel.content
        likes=prettyCount(dataModel.likes)+" Likes"
        comments=prettyCount(dataModel.comments)+" Comments"
    }

    fun prettyCount(number: Int): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue: Long = number.toLong()
        val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / Math.pow(
                    10.0,
                    base * 3.toDouble()
                )
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }
    /*companion object{
        *//**
         * Called when any image is need to be loaded in image view using url
         * @param imageView View
         * @param url url of image
         *//*
        @BindingAdapter("app:imageUrl")
        fun loadImage(imageView: ImageView?, url: String?) {
            Picasso.get().load(url).into(imageView)
        }
    }*/
}