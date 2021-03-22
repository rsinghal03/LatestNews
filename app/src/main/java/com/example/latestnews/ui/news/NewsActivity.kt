package com.example.latestnews.ui.news

import com.example.latestnews.R
import com.example.latestnews.databinding.ActivityBaseBinding
import com.example.latestnews.ui.base.BaseActivity
import com.example.latestnews.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsActivity : BaseActivity<ActivityBaseBinding>() {

    private val viewModel: NewsViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = viewModel

    override var layoutId: Int = R.layout.activity_top_news

}