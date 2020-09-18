package com.mp.myapplication.view.controller.services

import com.mp.myapplication.view.model.DataModel
import com.mp.myapplication.view.util.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetServices {

    /**
     * Called when Data needed to fetched from server and this method is use to create the url
     * @param page the index.
     * @param limit the limit of data to fetched
     * */
    @GET(AppConstants.url)
    suspend fun getData(@Query("page") page: Int?, @Query("limit") limit: Int?): Response<ArrayList<DataModel>>

}