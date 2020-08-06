package com.git.repolist.ui.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import com.git.repolist.R
import com.git.repolist.base.BaseViewModel
import com.git.repolist.data.api.RepositoryApi
import com.git.repolist.data.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class RepositoryViewModel(): BaseViewModel(){

    @Inject
    lateinit var repositoryApi: RepositoryApi
    private lateinit var subscription: Disposable

    // View related
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showRetry: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()

    // Model related
    val repositoryList: MutableLiveData<ArrayList<Repository>> = MutableLiveData()

    init {
        loadRepositoryList()
    }

    fun loadRepositoryList() {
        subscription = repositoryApi.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveRepoListStart() }
            .doOnTerminate { onRetrieveRepoListFinish() }
            .subscribe(
                { product -> onRetrieveRepoListSuccess(product) },
                { onRetrieveRepoListError() }
            )
    }

    private fun onRetrieveRepoListStart() {
        isLoading.value = true
        showRetry.value = false
        errorMessage.value = null
    }

    private fun onRetrieveRepoListFinish() {
        isLoading.value = false
        showRetry.value = false
    }

    private fun onRetrieveRepoListSuccess(response: ArrayList<Repository>) {
        repositoryList.value = response
    }

    private fun onRetrieveRepoListError() {
        isLoading.value = false
        showRetry.value = true
        errorMessage.value = R.string.error_api_general
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}