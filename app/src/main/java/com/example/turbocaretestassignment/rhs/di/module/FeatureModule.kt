package com.cpsl.has.rhs.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.turbocaretestassignment.rhs.app.base.ViewModelFactory
import com.example.turbocaretestassignment.rhs.di.module.MainActivityFeatureModule
import com.example.turbocaretestassignment.rhs.di.module.SelectVehicleFeatureModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

/**
 * @author yash gupta
 * Dagger Module class that includes all of the dagger modules of different features for the application
 */
@Module(includes = [
    SelectVehicleFeatureModule::class,
    MainActivityFeatureModule::class

])
class FeatureModule {
    /**
     * Provides view model factory
     */
    @Provides
    fun provideViewModelFactory(
            providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
            ViewModelFactory(providers)

}