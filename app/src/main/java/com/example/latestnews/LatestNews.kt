package com.example.latestnews

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.example.latestnews.di.applicationModule
import com.example.latestnews.ui.AppBindingAdapter
import com.example.latestnews.ui.AppBindingComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class LatestNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LatestNewsApplication)
            modules(modules = applicationModule)
        }

        Timber.plant(Timber.DebugTree())

        DataBindingUtil.setDefaultComponent(object : AppBindingComponent {
            override fun getAppBindingAdapter(): AppBindingAdapter {
                return AppBindingAdapter()
            }

        })
    }
}