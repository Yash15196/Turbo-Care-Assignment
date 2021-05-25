package com.example.turbocaretestassignment.rhs.di.module

import androidx.lifecycle.ViewModel
import com.cpsl.has.rhs.features.team.repo.VehicleRepo
import com.example.turbocaretestassignment.rhs.feature.service.VehicleService
import com.example.turbocaretestassignment.rhs.app.VehcileApp
import com.example.turbocaretestassignment.rhs.di.core.ViewModelKey
import com.example.turbocaretestassignment.rhs.feature.viewmodel.VehicleViewModel

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * abstract class that defines Modules for @see TaskViewModel, TaskRepo and TaskService
 * It uses repo and services from @see TaskFeatureModule
 */
abstract class VehicleListFeatureModule {
    @Module
    class ProvidesViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(VehicleViewModel::class)
        fun providesOdcViewModel(app: VehcileApp, repo: VehicleRepo): ViewModel = VehicleViewModel(app, repo)
    }

    @Module
    class ProvidesRepoAndService {
        @Provides
        @Singleton
        fun providesodcRepo(netService: VehicleService): VehicleRepo = VehicleRepo(netService)

        @Provides
        @Singleton
        fun providesOdcService(retrofit: Retrofit): VehicleService =
            retrofit.create(VehicleService::class.java)

    }


}