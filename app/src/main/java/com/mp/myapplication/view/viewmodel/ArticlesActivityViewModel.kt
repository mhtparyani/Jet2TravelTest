package com.mp.myapplication.view.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mp.myapplication.view.model.DataModel
import com.mp.myapplication.view.remote_service.ArticlesRemoteService
import com.mp.myapplication.view.remote_service.impl.ArticlesRemoteServiceImpl
import kotlinx.coroutines.launch

class ArticlesActivityViewModel : ViewModel(){
    var busy = ObservableField<Boolean>()
    var additionalFetch = ObservableField<Boolean>()
    var mutableDataList = MutableLiveData<ArrayList<DataModel>>()
    var isMoreAvailable: Boolean= false
    var dataList: ArrayList<DataModel> = ArrayList()
    private var articlesRemoteService: ArticlesRemoteService= ArticlesRemoteServiceImpl()
    /**
     * Called when images needs to be fetched from server
     * @param context Context
     * @param page page index
     * @param limit limit of data per page
     * */
    fun fetchData(page: Int?, limit: Int?){
        if (page==1) {
            busy.set(true)
            dataList= ArrayList()
        }
        else
            additionalFetch.set(true)
        viewModelScope.launch {
            val response = articlesRemoteService.getData(page, limit)
            val responseBody= response.body()
            if (response.isSuccessful && responseBody != null) {
                onSuccess(responseBody)
            } else {
                onError()
            }
        }
    }

    /**
     * On Data Receive*/
    private fun onSuccess(response: ArrayList<DataModel>) {
        if (response.size>0) {
            busy.set(false)
            additionalFetch.set(false)
            dataList.addAll(response)
            mutableDataList.value = dataList
            isMoreAvailable = response.size==10
        }else{
            busy.set(false)
            additionalFetch.set(false)
            isMoreAvailable=false
        }
    }
    /**
     * On Response Error*/
    private fun onError() {
        busy.set(false)
        additionalFetch.set(false)
    }
}