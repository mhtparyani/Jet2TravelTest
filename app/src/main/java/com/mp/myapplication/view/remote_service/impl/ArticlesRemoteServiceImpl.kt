package com.mp.myapplication.view.remote_service.impl

import com.mp.myapplication.view.controller.RetrofitModule
import com.mp.myapplication.view.controller.services.GetServices
import com.mp.myapplication.view.model.DataModel
import com.mp.myapplication.view.remote_service.ArticlesRemoteService
import retrofit2.Response

class ArticlesRemoteServiceImpl: ArticlesRemoteService {
    var service: GetServices

    init {
        val retrofit= RetrofitModule.providesRetrofit()
        service= retrofit.create(GetServices::class.java)
    }
    override suspend fun getData(page: Int?, limit: Int?)
            =service.getData(page,limit)
}