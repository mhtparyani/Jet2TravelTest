package com.mp.myapplication.view.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ArticlesActivityViewModel : ViewModel(){
    var busy = ObservableField<Boolean>()
    var additionalFetch = ObservableField<Boolean>()
}