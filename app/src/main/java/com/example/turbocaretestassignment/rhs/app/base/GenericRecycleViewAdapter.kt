package com.example.turbocaretestassignment.rhs.app.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import com.cpsl.has.rhs.domain.BaseEntity
import com.example.turbocaretestassignment.rhs.feature.RecordDiffCallBack

/**
 * @author: YASH
 *  A generic class for all kind of recyclerview adapter to be used in the app
 */
abstract class GenericRecycleViewAdapter<T : BaseEntity>(
        val context: FragmentActivity?,
        diffCallBack: RecordDiffCallBack<T>
) : ListAdapter<T, BaseRecyclerViewHolder<T>>(diffCallBack) {
    private val TAG = "GenericRecycleAdapter"
    protected val items = ArrayList<T>()

    /**
     * A method to be caled when a new list is to be shown in the recyclerview
     */
    open fun addItems(newList: List<T>) {
        items.clear()
        items.addAll(newList)
        submitList(newList)
        notifyDataSetChanged()
    }

    /**
     * A method to be caled when a new list of items to be added in already existing list
     */
    fun addNewItems(newList: List<T>) {
        val oldListSize = items.size
        items.addAll(newList)
        submitList(items)
        if (newList.isNotEmpty()) {
            val newListSize = items.size
            notifyItemRangeChanged(oldListSize, newListSize)
        } else {
            notifyItemRangeChanged(0, oldListSize)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<T> {
        return getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(getLayoutId(viewType), parent, false), viewType
        )
    }

    /**
     *  overridden method. It calls the bind method of BaseRecyclerViewHolder class to bind views
     */
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T> , position: Int) {
        holder.bind(position, getItem(position))
    }


    /**
     * This method provides the item view type based on the layout and item
     */
    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position)
    }

    /**
     * Overridden method to return the size of the list in adpater
     */
    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    open fun getItemList(): List<T> {
        return items
    }

    open fun addItem(newItem: T) {
        items.add(newItem)
        submitList(items)
        notifyDataSetChanged()
    }

    open fun deleteItemAt(position: Int) {
        items.removeAt(position)
        submitList(items)
        notifyDataSetChanged()
    }

    open fun deleteItemAtWithoutListUpdate(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    /**
     * An abstract method that must be overridden by the child classes to provide the view and corresponding view holder for each row
     */
    abstract fun getViewHolder(view: View, viewType: Int): BaseRecyclerViewHolder<T>

    /**
     * An abstract method that must be overridden by the child classes to provide the layout xml resourse for the corresponding row
     */
    protected abstract fun getLayoutId(position: Int): Int

}