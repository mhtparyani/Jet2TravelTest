package com.mp.myapplication.view.model

class DataModel {

    var id:Int = 0
    lateinit var createdAt: String
    lateinit var content:String
    var comments:Int = 0
    var likes:Int = 0
    lateinit var media: ArrayList<MediaModel>
    lateinit var user: ArrayList<UserModel>


}