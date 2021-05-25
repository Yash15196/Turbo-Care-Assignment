package com.example.turbocaretestassignment.rhs.feature

import android.os.Bundle
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.app.base.BaseActivity
import com.example.turbocaretestassignment.rhs.util.onBack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val fragment = VehicleHomeFragment()
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.container , fragment)
            .commit()
        toolbar.setNavigationOnClickListener() {
            onBack(this)
        }
    }


}