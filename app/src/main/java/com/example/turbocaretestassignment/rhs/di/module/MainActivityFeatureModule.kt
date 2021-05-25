package com.example.turbocaretestassignment.rhs.di.module

import com.example.turbocaretestassignment.rhs.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author yash.gupta
 * Dagger Module class for Home screen
 */
@Module
abstract class MainActivityFeatureModule {
    /**
     * function will ensure all dependencies in the HomeActivity are injected
     */
    @ContributesAndroidInjector
    abstract fun contributeActivity() : MainActivity

}