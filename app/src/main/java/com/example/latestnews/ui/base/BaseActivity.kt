package com.example.latestnews.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.latestnews.R
import com.example.latestnews.databinding.ActivityBaseBinding
import com.example.latestnews.extension.gone
import com.example.latestnews.extension.visible
import timber.log.Timber

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    private var baseViewModel: BaseViewModel? = null

    abstract fun getViewModel(): BaseViewModel

    var viewBinding: VB? = null

    abstract var layoutId: Int

    var activityBaseBinding: ActivityBaseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        activityBaseBinding?.executePendingBindings()
        activityBaseBinding?.lifecycleOwner = this
        viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            layoutId,
            activityBaseBinding?.layoutContainer,
            false
        )
        activityBaseBinding?.layoutContainer?.addView(viewBinding?.root)
        baseViewModel = getViewModel()
        initObserver()
    }

    private fun initObserver() {
        baseViewModel?.showProgressBar?.observe(this, { isShowProgress ->
            setLoader(isShowProgress)
        })
    }

    fun setLoader(isShowProgress: Boolean) {
        activityBaseBinding?.frameLayout?.let {
            Timber.d("progress bar")
            if (isShowProgress) it.visible() else it.gone()
        }
    }
}