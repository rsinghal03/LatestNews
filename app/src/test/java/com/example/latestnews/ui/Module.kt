package com.example.latestnews.ui

import com.example.latestnews.di.DEFAULT
import com.example.latestnews.di.IO
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.ui.news.NewsViewModel
import io.mockk.mockkClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val module = module {

    //mock repository
    single { mockkClass(NewsRepository::class) }


    //test coroutine dispatcher
    single(named(IO)) { CoroutineScope(TestCoroutineScope().coroutineContext) }
    single(named(DEFAULT)) { CoroutineScope(TestCoroutineScope().coroutineContext) }

    // viewModel
    single { NewsViewModel(get()) }

}