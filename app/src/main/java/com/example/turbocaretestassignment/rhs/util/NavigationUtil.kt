package com.example.turbocaretestassignment.rhs.util


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment




fun navigateToFragment(
    containerId: Int ,
    addToBackstack: Boolean ,
    context: AppCompatActivity ,
    fragment: Fragment
) {
    if (addToBackstack) {
        context.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()

    } else {
        context.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}

fun popAllFragment(context: AppCompatActivity){
    val fm = context.getSupportFragmentManager()
    for (i in 0 until fm.getBackStackEntryCount()) {
        fm.popBackStack()
    }

}
fun onBack(context: AppCompatActivity) {
    if(context.supportFragmentManager.backStackEntryCount <= 0) {
        context.onBackPressed()
    } else {
        context.supportFragmentManager.popBackStackImmediate()
    }}

fun showTitle(activity: AppCompatActivity,title:String){
    activity.setTitle(title)

}

class NavigationUtil {
}