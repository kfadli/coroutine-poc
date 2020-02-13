package com.kfadli.myapplication

import retrofit2.Retrofit


class ApiBuilder {

    fun createService(): ApiService {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        return retrofit.create<ApiService>(ApiService::class.java)
    }
}