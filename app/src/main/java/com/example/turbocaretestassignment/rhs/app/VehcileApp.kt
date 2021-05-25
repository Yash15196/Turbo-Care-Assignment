package com.example.turbocaretestassignment.rhs.app
import com.example.turbocaretestassignment.rhs.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * @author yash gupta
 * Application class. This will be starting point of the Application
 */
class VehcileApp : DaggerApplication() {
    /**
     * Method to inject Dagger dependencies
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).context(this).build()
    }
}