package com.kfadli.myapplication

import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("todos/{id}")
    suspend fun listRepos(@Path("id") id: Int? = 1): Todo
}