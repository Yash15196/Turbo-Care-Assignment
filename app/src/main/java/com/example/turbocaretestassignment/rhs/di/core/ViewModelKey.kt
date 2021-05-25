package com.example.turbocaretestassignment.rhs.di.core

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author yash gupta
 * This annotation ensures that ViewModels are kept in a map in key value pairs
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)