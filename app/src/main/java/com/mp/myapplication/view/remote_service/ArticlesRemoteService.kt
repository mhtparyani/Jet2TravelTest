package com.mp.myapplication.view.remote_service

import com.mp.myapplication.view.model.DataModel
import retrofit2.Response

interface ArticlesRemoteService {
    suspend fun getData(page: Int?, limit: Int?): Response<ArrayList<DataModel>>
}