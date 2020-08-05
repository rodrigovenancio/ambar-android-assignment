package com.git.repolist.injection.module

import com.rodrigo.buxtrading.BuildConfig
import com.rodrigo.buxtrading.data.api.AuthInterceptor
import com.rodrigo.buxtrading.data.api.BaseUrlChangingInterceptor
import com.rodrigo.buxtrading.data.api.ProductApi
import com.rodrigo.buxtrading.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.appflate.restmock.RESTMockServer
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG && RESTMockServer.getUrl() != null) {
            httpClient.addInterceptor(BaseUrlChangingInterceptor.get())
            httpClient.sslSocketFactory(RESTMockServer.getSSLSocketFactory(), RESTMockServer.getTrustManager())
        } else {
            httpClient.addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN))
        }

        return httpClient.build()
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}