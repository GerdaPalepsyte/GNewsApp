package com.example.gnewsapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected var item: T? = null

    fun bind(item: T) {
        this.item = item
        onBind(item)
    }

    abstract fun onBind(item: T)
}