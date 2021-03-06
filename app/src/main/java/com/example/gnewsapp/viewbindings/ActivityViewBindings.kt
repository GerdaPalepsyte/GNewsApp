package com.example.gnewsapp.viewbindings

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(layoutInflater) }