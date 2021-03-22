package com.example.latestnews.ui.base

import androidx.lifecycle.ViewModel
import com.example.githubrepos.util.SingleLiveEvent
import com.example.latestnews.di.DEFAULT
import com.example.latestnews.di.IO
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

abstract class BaseViewModel : ViewModel(), KoinComponent {

    val showProgressBar = SingleLiveEvent<Boolean>()

    val ioDispatcher: CoroutineScope by inject(named(IO))

    val defaultDispatcher: CoroutineScope by inject(named(DEFAULT))
}