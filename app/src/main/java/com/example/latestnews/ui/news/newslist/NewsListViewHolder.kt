package com.example.latestnews.ui.news.newslist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latestnews.R
import com.example.latestnews.databinding.NewsListItemBinding
import com.example.latestnews.model.remote.Article

class NewsListViewHolder(
    private val newsListItemBinding: NewsListItemBinding,
    private val itemClick: ((item: Article?) -> Unit)?
) : RecyclerView.ViewHolder(newsListItemBinding.root) {


    fun bind(item: Article?) {
        newsListItemBinding.run {
            newsAuthor.text = item?.author
            newsDescription.text = item?.description
        }
        Glide.with(itemView)
            .load(item?.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .into(newsListItemBinding.newsImage)
        bindClickListener(item)
    }

    private fun bindClickListener(item: Article?) {
        this.itemView.setOnClickListener {
            itemClick?.invoke(item)
        }
    }
}