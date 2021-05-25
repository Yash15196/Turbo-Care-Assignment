package com.example.turbocaretestassignment.rhs.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.util.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CreateVehicleProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showTitle(context as AppCompatActivity,getString(R.string.title_profile_new))

        fabNext.setOnClickListener() {
            if (etVehNo.text.toString().isBlank()) {
                Snackbar.make(it, getString(R.string.Vehcile_error), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                if (valid()) {
                    var bundle = Bundle()
                    var vehType: String? = null
                    if (rbFourWheeler.isChecked) {
                        vehType = "4w"
                    } else {
                        vehType = "2w"
                    }
                    bundle.putString(RECORD_VEH_NUMBER, etVehNo.text.toString())
                    bundle.putString(RECORD_VEH_TYPE, vehType)
                    val fragment = VehicleListFragment.newInstance(bundle, RECORD_VEH_NUMBER)
                    navigateToFragment(R.id.container, true, context as AppCompatActivity, fragment)
                } else {
                    Snackbar.make(it, getString(R.string.invalid_veh), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }

    private fun valid(): Boolean {
        val pattern = Regex("^[A-Z|a-z]{2}\\s?[0-9]{1,2}\\s?[A-Z|a-z]{0,3}\\s?[0-9]{4}$")
        return etVehNo.text.toString().matches(pattern)

    }
}