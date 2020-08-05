package com.rodrigo.buxtrading.base

import androidx.lifecycle.ViewModel
import com.rodrigo.buxtrading.injection.module.NetworkModule
import com.rodrigo.buxtrading.injection.component.DaggerViewModelInjector
import com.rodrigo.buxtrading.injection.component.ViewModelInjector
import com.rodrigo.buxtrading.ui.product.viewmodel.ProductViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ProductViewModel -> injector.inject(this)
        }
    }
}