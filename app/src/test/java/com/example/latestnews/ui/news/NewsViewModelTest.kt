package com.example.latestnews.ui.news

import androidx.paging.PagingData
import com.example.latestnews.model.remote.Article
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.ui.BaseTest
import com.example.latestnews.util.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.test.inject


@ExperimentalCoroutinesApi
class NewsViewModelTest : BaseTest() {

    private val newsViewModel: NewsViewModel by inject()
    private val newsRepository: NewsRepository by inject()

    @Before
    override
    fun setUp() {
        super.setUp()
        coEvery { newsRepository.fetchTopNews(10, any()) } just runs
    }

    @Test
    fun fetchTopNews() {
        newsViewModel.fetchTopNews()
        coVerify { newsRepository.fetchTopNews(10, any()) }
    }

    @Test
    fun subscribeToPagingData() {
        val expected = PagingData.from(listOf<Article>())
        TestCoroutineScope().launch {
            val flow =
                    MutableSharedFlow<PagingData<Article>>(replay = 1).apply { emit(expected) }
            every { newsRepository.flow } returns flow
        }
        Assert.assertEquals(expected, newsViewModel.subscribeToPagingData().getOrAwaitValue())
    }
}