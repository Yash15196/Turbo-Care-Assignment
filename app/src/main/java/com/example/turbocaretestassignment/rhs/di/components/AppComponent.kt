package com.example.turbocaretestassignment.rhs.di.components

import android.content.Context
import com.example.turbocaretestassignment.rhs.app.VehcileApp
import com.example.turbocaretestassignment.rhs.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author yash gupta
 * This class is the main component that includes all other modules and components
 * @see VehcileApp class uses this to create dependency graph
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<VehcileApp> {
    /**
     * overridden function to inject dependencies
     */
    override fun inject(app: VehcileApp)

    /**
     * Builder . It will create dependency graph after compilation
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: VehcileApp): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}