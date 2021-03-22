package com.example.latestnews.ui.news.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.latestnews.R
import com.example.latestnews.databinding.NewsListItemBinding
import com.example.latestnews.model.remote.Article

class NewsListAdapter : PagingDataAdapter<Article, NewsListViewHolder>(ITEM_COMPARATOR) {

    var itemClickListener: ((item: Article?) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding: NewsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item,
            parent,
            false
        )
        return NewsListViewHolder(binding, itemClickListener)
    }

    companion object {
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }

}