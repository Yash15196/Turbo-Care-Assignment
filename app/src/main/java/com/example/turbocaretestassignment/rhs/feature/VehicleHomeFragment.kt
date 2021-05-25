package com.example.turbocaretestassignment.rhs.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.app.db.AppDB
import com.example.turbocaretestassignment.rhs.domain.VehicleLocalDbEntity
import com.example.turbocaretestassignment.rhs.util.navigateToFragment
import com.example.turbocaretestassignment.rhs.util.showTitle
import kotlinx.android.synthetic.main.activity_home_vehcile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VehicleHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for context fragment
        return inflater.inflate(R.layout.activity_home_vehcile , container , false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.show()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchList()
        showTitle(context as AppCompatActivity,getString(R.string.vehicle))
        fab.setOnClickListener { view ->
            var fragment = CreateVehicleProfileFragment()
            navigateToFragment(R.id.container , true , context as AppCompatActivity , fragment)

        }
    }




    private fun fetchList() {
        val db = context?.let { AppDB(it) }
        GlobalScope.launch {

            val list = db?.vehDao()?.getAll()
            if (list != null) {
                activity?.runOnUiThread(Runnable {
                    initRecyclerView(list)
                })

            }


        }
    }


    private fun initRecyclerView(list: List<VehicleLocalDbEntity>) {
        vgVehList.layoutManager =
            LinearLayoutManager(context , RecyclerView.VERTICAL , false)
        val listAdapter =
            VehListAdapterAdapter(context , true , list as ArrayList<VehicleLocalDbEntity>)
        vgVehList.adapter = listAdapter
    }
}