package com.mp.myapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mp.myapplication.R
import com.mp.myapplication.databinding.ActivityArticlesBinding
import com.mp.myapplication.view.adapter.ArticlesActivityAdapter
import com.mp.myapplication.view.viewmodel.ArticlesActivityViewModel

class ArticlesActivity : AppCompatActivity() {

    private val viewModel by viewModels<ArticlesActivityViewModel>()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    var page: Int = 1
    private var isScrolling: Boolean = false
    private lateinit var articlesActivityAdapter: ArticlesActivityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Binding the layout with kotlin class
         * */
        val binding: ActivityArticlesBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_articles
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) /** This line creates the list view which is to loaded in recycler view*/
        binding.list.layoutManager = layoutManager
        binding.list.setHasFixedSize(false)
        recyclerView = binding.list /** Recyclerview initializer*/
        articlesActivityAdapter= ArticlesActivityAdapter()
        recyclerView.adapter=articlesActivityAdapter
        viewModel.fetchData(this,1,10)
        /**
         *Below code observes the images from server are fetched so that images can be loaded on screen
         * */
        viewModel.mutableDataList.observe(this, androidx.lifecycle.Observer { items ->
            articlesActivityAdapter.updateImageList(items)
        })

    }
}