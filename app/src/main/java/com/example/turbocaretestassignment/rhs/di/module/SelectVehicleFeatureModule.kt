package com.example.turbocaretestassignment.rhs.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.turbocaretestassignment.rhs.feature.VehicleListFragment
import com.example.turbocaretestassignment.rhs.feature.viewmodel.VehicleViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * @author YASH
 * Dagger Module class for OtisDoss detail
 */
@Module(includes = [VehicleListFeatureModule.ProvidesViewModel::class, VehicleListFeatureModule.ProvidesRepoAndService::class])
abstract class SelectVehicleFeatureModule : VehicleListFeatureModule() {

    // defines contributing modules for OnlineTransferListFeatureModule
    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    /**
     * Dagger will bind to @see OnlineTransferDetailFragment
     */
    abstract fun bind() : VehicleListFragment

    /**
     * Inner class. Provides @see LoginViewModel for @see OnlineTransferDetailFragment
     */
    @Module
    class InjectViewModel {
        @Provides
        fun provideOtisDossViewModel(
            factory: ViewModelProvider.Factory,
            target: VehicleListFragment
        ):VehicleViewModel = ViewModelProviders.of(target, factory).get(VehicleViewModel::class.java)
    }
}