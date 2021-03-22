package com.example.latestnews.ui.news.newsdescription

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.bumptech.glide.Glide
import com.example.latestnews.R
import com.example.latestnews.databinding.FragmentNewsDescriptionBinding
import com.example.latestnews.extension.replaceSlash
import com.example.latestnews.ui.base.BaseFragment
import com.example.latestnews.util.NEWS_CONTENT
import com.example.latestnews.util.NEWS_URL
import com.example.latestnews.util.NEWS_URL_TO_IMAGE
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URI

//https://stackoverflow.com/questions/51984209/how-to-get-full-news-content-from-news-api

class NewsDescriptionFragment :
    BaseFragment<FragmentNewsDescriptionBinding, NewsDescriptionViewModel>() {

    private val newsDescriptionViewModel: NewsDescriptionViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_news_description

    override fun getViewModel(): NewsDescriptionViewModel = newsDescriptionViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(NEWS_URL)
        val articleId = with(URI(url)) { (host + path).replaceSlash() }
        newsDescriptionViewModel.fetchAdditionalInfo(articleId)
        viewDataBinding?.newsDescriptionContent?.text = requireArguments().getString(NEWS_CONTENT)
        viewDataBinding?.let {
            Glide.with(it.descriptionConstraint.rootView)
                .load(requireArguments().getString(NEWS_URL_TO_IMAGE))
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .into(it.newsDescriptionImage)
        }

    }
}