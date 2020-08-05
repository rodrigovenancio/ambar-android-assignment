package com.git.repolist.injection.component

import com.git.repolist.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
/*
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    */
/**
     * Injects required dependencies into the specified ProductViewModel.
     * @param productViewModel ProductViewModel in which to inject the dependencies
     *//*

    fun inject(productViewModel: ProductViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}*/
