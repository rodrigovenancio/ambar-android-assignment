package com.git.repolist.injection.component

import com.git.repolist.injection.module.NetworkModule
import com.git.repolist.ui.repository.viewmodel.RepositoryViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

/**
     * Injects required dependencies into the specified RepositoryViewModel.
     * @param repositoryViewModel RepositoryViewModel in which to inject the dependencies
     */

    fun inject(repositoryViewModel: RepositoryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
