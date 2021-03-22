package com.example.latestnews.ui.news.newslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.latestnews.R
import com.example.latestnews.databinding.FragmentNewsListBinding
import com.example.latestnews.ui.base.BaseFragment
import com.example.latestnews.ui.news.NewsViewModel
import com.example.latestnews.util.NEWS_CONTENT
import com.example.latestnews.util.NEWS_TITLE
import com.example.latestnews.util.NEWS_URL
import com.example.latestnews.util.NEWS_URL_TO_IMAGE
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewsListFragment : BaseFragment<FragmentNewsListBinding, NewsViewModel>() {

    private val newsViewModel: NewsViewModel by sharedViewModel()

    override fun getLayoutId(): Int = R.layout.fragment_news_list

    override fun getViewModel(): NewsViewModel = newsViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    private val newsListAdapter: NewsListAdapter by lazy { NewsListAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeToRefresh()
    }

    /**
     * Initialise adapter
     *
     */
    private fun initAdapter() {
        setRecyclerAttr()
        listItemClickListener()
        addLoadStateListener()
        registerProgressBar()
        observePagingData()
    }

    /**
     * Set recycler view property
     *
     */
    private fun setRecyclerAttr() {
        viewDataBinding?.list?.run {
            adapter = newsListAdapter
            setHasFixedSize(true)
            adapter = newsListAdapter.withLoadStateFooter(
                footer = NewsListLoadStateAdapter { newsListAdapter.retry() }
            )
        }
    }

    /**
     * Observe paging data source and submit data to adapter
     *
     */
    private fun observePagingData() {
        newsViewModel.subscribeToPagingData().observe(viewLifecycleOwner, {
            newsListAdapter.submitData(lifecycle, it)
        })
    }

    /**
     * Show progress bar when Load state is Loading
     *
     */
    private fun registerProgressBar() {
        lifecycleScope.launchWhenCreated {
            newsListAdapter.loadStateFlow.collectLatest { loadStates ->
                viewDataBinding?.swipeRefresh?.isRefreshing =
                    loadStates.refresh is LoadState.Loading
            }
        }
    }

    /**
     * Add load state listener to show error state of the paging source data
     *
     */
    private fun addLoadStateListener() {
        newsListAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        (it.refresh as LoadState.Error).error.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }

    /**
     * on list item click, navigate to details fragment
     *
     */
    private fun listItemClickListener() {
        newsListAdapter.itemClickListener = {
            val bundle = bundleOf(
                NEWS_CONTENT to it?.content,
                NEWS_TITLE to it?.title,
                NEWS_URL to it?.url,
                NEWS_URL_TO_IMAGE to it?.urlToImage
            )
            findNavController().navigate(
                R.id.action_newsListFragment_to_newsDescriptionFragment,
                bundle
            )
        }
    }

    /**
     * sets the refresh listener
     *
     */
    private fun initSwipeToRefresh() {
        viewDataBinding?.swipeRefresh?.setOnRefreshListener { newsListAdapter.refresh() }
    }
}