package com.amoure.suitmediatest.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.amoure.suitmediatest.data.response.UserItem
import com.amoure.suitmediatest.data.retrofit.ApiService

class UserRepository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<UserItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}