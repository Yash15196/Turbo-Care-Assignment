package com.example.turbocaretestassignment.rhs

import android.app.Application
import androidx.lifecycle.AndroidViewModel


abstract class BaseViewModel(application: Application):AndroidViewModel(application) {
    override fun onCleared() {
       claredViewModel()
        super.onCleared()
    }

    protected abstract fun claredViewModel()
}
