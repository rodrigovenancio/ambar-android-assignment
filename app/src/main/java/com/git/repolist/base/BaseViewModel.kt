package com.git.repolist.base

import androidx.lifecycle.ViewModel
import com.git.repolist.injection.component.DaggerViewModelInjector
import com.git.repolist.injection.component.ViewModelInjector
import com.git.repolist.injection.module.NetworkModule
import com.git.repolist.ui.repository.viewmodel.RepositoryViewModel

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
            is RepositoryViewModel -> injector.inject(this)
        }
    }
}