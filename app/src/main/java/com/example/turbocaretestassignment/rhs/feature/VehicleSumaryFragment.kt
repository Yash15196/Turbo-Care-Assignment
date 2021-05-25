package com.example.turbocaretestassignment.rhs.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.app.db.AppDB
import com.example.turbocaretestassignment.rhs.domain.VehicleLocalDbEntity
import com.example.turbocaretestassignment.rhs.util.*
import kotlinx.android.synthetic.main.fragment_vehicle_sumary.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [VehicleSumaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehicleSumaryFragment : Fragment() {


    var isShowing = false

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_sumary , container , false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvFuel.text = arguments?.getString(RECORD_VEH_FUEL)
        tvMake.text = arguments?.getString(RECORD_VEH_NAME)
        tvModel.text = arguments?.getString(RECORD_VEH_MODEL)
        tvTransmission.text = arguments?.getString(RECORD_VEH_TRANS)
        tvVehNo.text = arguments?.getString(RECORD_VEH_NUMBER)
        tvVehBrand.text = arguments?.getString(RECORD_VEH_MODEL)
        if (isShowing) {
            fabNext.visibility = View.VISIBLE
        } else {
            fabNext.visibility = View.GONE

        }
        fabNext.setOnClickListener() {
            var db = context?.let { it1 -> AppDB(it1) }
            GlobalScope.launch {
                var vehicleLocalDbEntity = VehicleLocalDbEntity(
                    0 ,
                    arguments?.getString(RECORD_VEH_NAME) ?: "" ,
                    arguments?.getString(RECORD_VEH_NUMBER) ?: "" ,
                    arguments?.getString(RECORD_VEH_MODEL) ?: "" ,
                    arguments?.getString(RECORD_VEH_FUEL) ?: "" ,
                    arguments?.getString(RECORD_VEH_TRANS) ?: ""

                )

                db?.vehDao()?.insertAll(vehicleLocalDbEntity)
                popAllFragment(context as AppCompatActivity)
                var fragment = VehicleHomeFragment()
                navigateToFragment(R.id.container , false , context as AppCompatActivity , fragment)


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       if(isShowing) {
           (activity as AppCompatActivity?)?.supportActionBar?.hide()
       }else{
           showTitle(context as AppCompatActivity,"Vehicle Details")

       }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VehicleSumaryFragment.
         */
        @JvmStatic
        fun newInstance(bundle: Bundle , b: Boolean) =
            VehicleSumaryFragment().apply {
                arguments = bundle
                isShowing = b
            }
    }
}