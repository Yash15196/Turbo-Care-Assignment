package com.example.turbocaretestassignment.rhs.di.module

import com.example.turbocaretestassignment.rhs.app.VehcileApp
import com.cpsl.has.rhs.di.core.NetworkModule
import com.cpsl.has.rhs.di.module.FeatureModule
import dagger.Module

/**
 * @author yash gupta
 * Basic App Module class for providing dagger dependencies
 * It includes all the other modules in itself and is used by @see RhsAppComponent
 */
@Module (includes = [FeatureModule::class, NetworkModule::class])
class AppModule(val app: VehcileApp)