package com.amoure.suitmediatest.data.retrofit

import com.amoure.suitmediatest.data.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") size: Int = 20
    ): UsersResponse
}