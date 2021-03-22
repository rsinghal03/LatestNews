package com.example.latestnews.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent

interface AppBindingComponent : DataBindingComponent {
    override fun getAppBindingAdapter(): AppBindingAdapter
}


class AppBindingAdapter() {

    // BindingAdapter : we can write methods with binding adapter

    @BindingAdapter("bind_text", requireAll = false)
    fun setAppearance(view: TextView, text: String?) {
        view.text = text
    }
}