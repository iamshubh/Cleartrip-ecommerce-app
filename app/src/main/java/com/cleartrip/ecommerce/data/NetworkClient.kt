package com.cleartrip.ecommerce.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    const val URL = "https://dummyjson.com/"

    var mService: ApiService? = null

    fun getService(): ApiService {
        if (mService == null) {
            val retrofiet =
                Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .build()

            mService = retrofiet.create(ApiService::class.java)
        }
        return mService!!
    }
}