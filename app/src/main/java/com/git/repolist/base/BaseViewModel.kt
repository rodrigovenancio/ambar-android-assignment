package com.git.repolist.base

import androidx.lifecycle.ViewModel
import com.git.repolist.injection.module.NetworkModule

abstract class BaseViewModel:ViewModel(){
    /*private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    *//**
     * Injects the required dependencies
     *//*
    private fun inject() {
        when (this) {
            is RepositoryListViewModel -> injector.inject(this)
        }
    }*/
}