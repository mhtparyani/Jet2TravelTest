package com.mp.myapplication.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mp.myapplication.R
import com.mp.myapplication.databinding.ActivityArticlesBinding
import com.mp.myapplication.view.adapter.ArticlesActivityAdapter
import com.mp.myapplication.view.viewmodel.ArticlesActivityViewModel


class ArticlesActivity : AppCompatActivity(), ScrollLastItem {

    private val viewModel by viewModels<ArticlesActivityViewModel>()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    var page: Int = 1
    var limit: Int=10
    private var isScrolling: Boolean = false
    private lateinit var articlesActivityAdapter: ArticlesActivityAdapter

    private val SECOND_MILLIS = 1000
    private val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private val DAY_MILLIS = 24 * HOUR_MILLIS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Binding the layout with kotlin class
         * */
        val binding: ActivityArticlesBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_articles
        )
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        val inflator = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflator.inflate(R.layout.layout_title, null)
        supportActionBar?.customView=v
        binding.viewModel  = viewModel
        binding.lifecycleOwner = this
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        /** This line creates the list view which is to loaded in recycler view*/
        binding.list.layoutManager = layoutManager
        binding.list.setHasFixedSize(false)
        recyclerView = binding.list
        /** Recyclerview initializer*/
        articlesActivityAdapter = ArticlesActivityAdapter()
        recyclerView.adapter = articlesActivityAdapter
        viewModel.fetchData( page, limit)
        /**
         *Below code observes the images from server are fetched so that images can be loaded on screen
         * */
        viewModel.mutableDataList.observe(this, androidx.lifecycle.Observer { items ->
            articlesActivityAdapter.updateImageList(items)
        })
        /**
         * Below code line use to listen for scroll
         * */
        recyclerView.addOnScrollListener(OnScrollEndLoadMore())
    }
    /**
     * Called when last image is shown to the user so that next page is to be fetched
     * */
    override fun scrollLastItem() {
        recyclerView.scrollToPosition(articlesActivityAdapter.itemCount - 1)
    }

    inner class OnScrollEndLoadMore : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val currentItems = layoutManager.childCount
            val totalItems = layoutManager.itemCount
            val scrollOutItems = layoutManager.findFirstVisibleItemPosition()
            if(isScrolling && (currentItems + scrollOutItems == totalItems) && viewModel.isMoreAvailable){
                fetchExtraImages()
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    /**
     * Called when paging is called
     * */
    private fun fetchExtraImages() {
        page++
        isScrolling=false
        viewModel.fetchData(page,limit)
    }
}

interface ScrollLastItem {
    fun scrollLastItem()
}
