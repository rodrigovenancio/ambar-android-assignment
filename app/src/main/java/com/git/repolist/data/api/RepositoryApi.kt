package com.git.repolist.data.api

import com.git.repolist.data.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface RepositoryApi {
    /**
     * Get the details of the given Product from the API
     */
    @GET("repositories")
    fun getRepositories(): Observable<ArrayList<Repository>>
}