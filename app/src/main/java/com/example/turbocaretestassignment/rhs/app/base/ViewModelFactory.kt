package com.example.turbocaretestassignment.rhs.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

/**
 * @author yash gupta
 * A factory class for ViewModelProviders.
 * It ensures that the ViewModels are kept in key value pairs and can be retrieved whenever is required
 */
class ViewModelFactory(
    private val providers: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    /**
     * Overridden method to create view model classes based on the argument supplied
     * @param modelClass: Class for which the view model is being created
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        requireNotNull(getProvider(modelClass).get()) {
            "Provider for $modelClass returned null"
        }

    /**
     * Method to get the provider for the view model.
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T : ViewModel> getProvider(modelClass: Class<T>): Provider<T> =
        try {
            requireNotNull(providers[modelClass] as Provider<T>) {
                "No ViewModel provider is bound for class $modelClass"
            }
        } catch (cce: ClassCastException) {
            error("Wrong provider type registered for ViewModel type $modelClass")
        }

}