package com.amoure.suitmediatest.di

import com.amoure.suitmediatest.data.UserRepository
import com.amoure.suitmediatest.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}