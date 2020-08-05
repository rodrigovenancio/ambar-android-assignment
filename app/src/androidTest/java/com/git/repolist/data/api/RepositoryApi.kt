package com.git.repolist.data.api

import com.rodrigo.buxtrading.data.model.Product
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The interface which provides methods to get result of webservices
 */
interface ProductApi {
    /**
     * Get the details of the given Product from the API
     */
    @GET("repositories")
    fun getProductDetails(@Path("id") id: String): Observable<Product>
}