package com.example.turbocaretestassignment.rhs.app.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cpsl.has.rhs.domain.BaseEntity


/**
 * @author YASH
 * An abstract Generic recyclerview holder class.
 * It ensures that whole app follows same pattern while implementing a recyclerview
 */
abstract class BaseRecyclerViewHolder<T : BaseEntity>(
    itemView: View,
    val eventListener: ViewHolderEventListener<T>
) : RecyclerView.ViewHolder(itemView) {


    /**
     * This method to be overridden by child classes and all the views related code should go here.
     * A bind method must be called from onBindViewHolder() method of the Adapter classes to ensure that the views
     */

    abstract fun bind(position: Int, item: T)
}