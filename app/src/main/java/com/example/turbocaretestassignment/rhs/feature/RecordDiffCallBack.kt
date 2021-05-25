package com.example.turbocaretestassignment.rhs.feature

import androidx.recyclerview.widget.DiffUtil

open class RecordDiffCallBack<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }


}
