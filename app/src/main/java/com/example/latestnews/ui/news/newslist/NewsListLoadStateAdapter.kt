package com.example.latestnews.ui.news.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.latestnews.databinding.NetworkStateItemBinding

class NewsListLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<NewsListLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            NetworkStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class LoadStateViewHolder(private val networkStateItemBinding: NetworkStateItemBinding) :
        RecyclerView.ViewHolder(networkStateItemBinding.root) {

        fun bind(loadState: LoadState) {
            val progress = networkStateItemBinding.progressBar
            val btnRetry = networkStateItemBinding.retryButton
            val txtErrorMessage = networkStateItemBinding.errorMsg

            progress.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            txtErrorMessage.isVisible = loadState !is LoadState.Loading

            if (loadState is LoadState.Error) {
                txtErrorMessage.text = loadState.error.localizedMessage
            }

            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }
}