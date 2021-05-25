package com.cpsl.has.rhs.features.bss.ui.common

import android.os.Bundle
import android.view.*
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.cpsl.has.rhs.domain.BaseEntity
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.app.base.BaseFragment

import com.example.turbocaretestassignment.rhs.app.base.GenericRecycleViewAdapter
import com.example.turbocaretestassignment.rhs.feature.RecordDiffCallBack

/**
 * @author YASH
 * A base class for All Fagments in BSS Module that uses recyclerview.
 * The generic type <T> defines the object to be used in the recylerview adapter
 * Please note that this fragment assumes that the id of RecyclerView in the XML Layout is always vgDataContainer
 * It also assumes that to show no data view a view is included with the ID - incErrorMessage
 */
abstract class BaseRecyclerViewFragment<T : BaseEntity, V : AndroidViewModel> : BaseFragment<V>() {
    private val TAG = "BssBaseRecyclerFragment"
    private var bssAdapter: GenericRecycleViewAdapter<T>? = null // generic adapter
    protected var diffCategory: RecordDiffCallBack<T> = RecordDiffCallBack()
    private var vgDataContainer: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    /**
     * Ensures the subclasses embedded view is used
     * Note that subclasses, must not override this method,
     * instead subclasses should rely on overridding getViewLayout() method to provide layout id to be used in this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getViewLayout(), container, false)
    }


    /**
     * Ensures that the subclasses uses initializes ui
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView() // to initialize the recycler view
        setupViews()

    }


    /**
     * Function to returns the bssAdapter
     * @return bssAdapter
     */
    protected fun getRecyclerViewAdapter(): GenericRecycleViewAdapter<T>? {
        return bssAdapter
    }

    /**
     * Initializes the recyclerview of this screen
     */
    private fun initRecyclerView() {
        bssAdapter = provideRecyclerViewAdapter() // initializes recycler view generic adapter
        vgDataContainer = view?.findViewById(R.id.vgDataContainer)
        vgDataContainer?.layoutManager = getRecyclerViewLayoutManager()
        vgDataContainer?.adapter = getRecyclerViewAdapter()
    }


    /**
     * Function to clear existing items in the adapter and add new list to the adapter
     * @param newList: New List containing the items
     */
    protected fun addItems(newList: List<T>) {
        bssAdapter?.addItems(newList)
    }

    /**
     * Function to clear existing items in the adapter and add new list to the adapter
     * @param newList: New List containing the items
     */
    protected fun addNewItems(newList: List<T>) {
        bssAdapter?.addNewItems(newList)
    }


    /**
     * Abstract method must be overridden by subclasses to provide layout xml id for the fragment
     */
    protected abstract fun getViewLayout(): Int

    /**
     * Abstract method must be overridden by subclasses to ensure that the view is initialized properly.
     * This method should be used to initialize all the views other than Recyclerview in the screen
     * This is called from activity lifecycle method onActivityCreated(). and it ensures that it is called before the api method are called
     */
    protected abstract fun setupViews()

    /**
     * The function to initialize generic adapter.
     * The subclasses must provide an implementation of the generic adapter that should then be assigned to bssAdapter.
     * Subclasses must further use this adapter only
     */
    abstract fun provideRecyclerViewAdapter(): GenericRecycleViewAdapter<T>?

    /*
    To allow screens to provide title textt
     */
    abstract fun fetchScreenTitle(): String


    /**
     * Method to provide the recyclerview manager
     */
    abstract fun getRecyclerViewLayoutManager(): RecyclerView.LayoutManager


}