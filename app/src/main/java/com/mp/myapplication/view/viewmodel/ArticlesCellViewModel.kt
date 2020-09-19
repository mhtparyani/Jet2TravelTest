package com.mp.myapplication.view.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.mp.myapplication.view.model.DataModel
import com.squareup.picasso.Picasso

class ArticlesCellViewModel: ViewModel() {

    lateinit var dataModel: DataModel
    lateinit var userName:String
    lateinit var userDesignation:String
    lateinit var article_content:String
    lateinit var article_title:String
    lateinit var article_url:String
    var likes:Int = 0
    var comments:Int = 0
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