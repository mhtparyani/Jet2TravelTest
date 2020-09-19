package com.mp.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mp.myapplication.R
import com.mp.myapplication.databinding.ArticlesCellLayoutBinding
import com.mp.myapplication.view.model.DataModel
import com.mp.myapplication.view.viewmodel.ArticlesCellViewModel

class ArticlesActivityAdapter : RecyclerView.Adapter<ArticlesActivityAdapter.ViewHolder>(){

    lateinit var dataModelList:ArrayList<DataModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ArticlesCellLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.articles_cell_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::dataModelList.isInitialized) dataModelList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataModelList[position])
    }
    /**
     * Called when data is fetched from server
     * */
    fun updateImageList(dataModelList: ArrayList<DataModel>) {
        this.dataModelList = dataModelList
        notifyDataSetChanged()
    }

    class ViewHolder(private  val  binding: ArticlesCellLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val viewModel = ArticlesCellViewModel();
        fun bind(datamodel: DataModel) {
            viewModel.bind(datamodel)
            binding.viewModel = viewModel
        }
    }
}