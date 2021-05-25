package com.example.turbocaretestassignment.rhs.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cpsl.has.rhs.features.bss.ui.common.BaseRecyclerViewFragment
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.domain.VehicleListEnity
import com.example.turbocaretestassignment.rhs.feature.viewmodel.VehicleViewModel
import androidx.lifecycle.Observer
import com.example.turbocaretestassignment.rhs.app.base.*
import com.example.turbocaretestassignment.rhs.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VehicleListFragment : BaseRecyclerViewFragment<VehicleListEnity , VehicleViewModel>() {
    var vehicleType: String? = null
    var screenName: String? = null
    var CurrentscreenName: String? = null

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment TeamCompositionFragment.
         */
        @JvmStatic
        fun newInstance(bundle: Bundle , screenName: String): VehicleListFragment {
            val fragment = VehicleListFragment()
            fragment.arguments = bundle
            fragment.screenName = screenName
            return fragment
        }
    }

//    override fun initViewModel(): VehicleViewModel {
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VehicleViewModel::class.java)
//        return viewModel
//    }

    override fun getViewLayout(): Int {
        return R.layout.fragment_second
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun setupViews() {
        vehicleType = arguments?.getString(RECORD_VEH_TYPE)

        if (screenName.equals(RECORD_VEH_NUMBER)) {
            callVehList()
            CurrentscreenName = RECORD_VEH_NAME
            showTitle(context as AppCompatActivity,"Select Make")

        } else if (screenName.equals(RECORD_VEH_NAME)) {
            callVehModel()
            CurrentscreenName = RECORD_VEH_MODEL
            showTitle(context as AppCompatActivity,"Select Model")
        } else if (screenName.equals(RECORD_VEH_MODEL)) {
            val list = resources.getStringArray(R.array.fuel_type).toList()
            parseList(list as ArrayList<String>)
            CurrentscreenName = RECORD_VEH_FUEL
            showTitle(context as AppCompatActivity,"Select Fuel Type")
        } else if (screenName.equals(RECORD_VEH_FUEL)) {
            CurrentscreenName = RECORD_VEH_TRANS
            showTitle(context as AppCompatActivity,"Select Transmission")

            val list = resources.getStringArray(R.array.transmission_type).toList()
            parseList(list as ArrayList<String>)
        }

    }

    fun parseList(vehList: ArrayList<String>) {
        var parseList: ArrayList<VehicleListEnity> = ArrayList()
        for (entity in vehList) {
            val vehicleListEnity = VehicleListEnity(0)
            vehicleListEnity.vehicleName = entity
            parseList.add(vehicleListEnity)
        }
        addItems(parseList)


    }

    fun callVehModel() {

        arguments?.getString(RECORD_VEH_NAME)?.let {
            viewModel.getVehModel(vehicleType , it)
                .observe(
                    this ,
                    Observer<LiveDataResultWrapper<ArrayList<VehicleListEnity>>> { result ->
                        result?.let {
                            handleVehListResult(result)
                        }
                    })
        }


    }

    fun callVehList() {

        viewModel.getVehList(vehicleType)
            .observe(this , Observer<LiveDataResultWrapper<ArrayList<VehicleListEnity>>> { result ->
                result?.let {
                    handleVehListResult(result)
                }
            })


    }

    private fun handleVehListResult(result: LiveDataResultWrapper<ArrayList<VehicleListEnity>>) {
        when (result.resultStatus) {
            LiveDataResultStatus.LOADING -> {
                showHideProgressBar(true)
            }
            LiveDataResultStatus.ERROR -> {
                if (result.error != null) {

                    Toast.makeText(activity , "some errror occured" , Toast.LENGTH_LONG).show()
                }
                showHideProgressBar(false)

            }
            else -> {
                showHideProgressBar(false)
                result.resultData?.let { addItems(it) }
            }
        }
    }

    override fun provideRecyclerViewAdapter(): GenericRecycleViewAdapter<VehicleListEnity>? {
        return object : GenericRecycleViewAdapter<VehicleListEnity>(activity , diffCategory) {
            override fun getViewHolder(
                view: View ,
                viewType: Int
            ): BaseRecyclerViewHolder<VehicleListEnity> {
                return VehicleListViewHolder(view , listner)
            }

            override fun getLayoutId(position: Int): Int {
                return R.layout.item_vehicle_list
            }

        }
    }

    private var listner = object :
        ViewHolderEventListener<VehicleListEnity> {
        override fun onItemEvent(
            eventType: Int ,
            position: Int ,
            item: VehicleListEnity ,
            bundle: Bundle?
        ) {

            var fragment: Fragment? = null
            arguments?.putString(CurrentscreenName , item.vehicleName)
            if (CurrentscreenName?.equals(RECORD_VEH_TRANS) == true) {
                fragment = arguments?.let { VehicleSumaryFragment.newInstance(it , true) }
            }else {
                fragment = arguments?.let { CurrentscreenName?.let { it1 -> newInstance(it , it1) } }

            }
                if (fragment != null) {
                    navigateToFragment(
                        R.id.container ,
                        true ,
                        context as AppCompatActivity ,
                        fragment
                    )
                }

            }


        override fun onListEvent(
            eventType: Int ,
            position: Int ,
            item: List<VehicleListEnity> ,
            bundle: Bundle?
        ) {
        }

    }

    override fun fetchScreenTitle(): String {
        return ""
    }

    override fun getRecyclerViewLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context , RecyclerView.VERTICAL , false)
    }

    override fun initViewModel(): VehicleViewModel {
        return ViewModelProviders.of(this , viewModelFactory).get(VehicleViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        showHideProgressBar(false)
    }
}