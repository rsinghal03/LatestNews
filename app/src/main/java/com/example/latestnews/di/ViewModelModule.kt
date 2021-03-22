package com.example.latestnews.di

import com.example.latestnews.ui.news.NewsViewModel
import com.example.latestnews.ui.news.newsdescription.NewsDescriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NewsViewModel(get()) }
    viewModel { NewsDescriptionViewModel(get()) }
}