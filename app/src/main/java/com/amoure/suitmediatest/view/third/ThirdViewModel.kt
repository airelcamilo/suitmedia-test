package com.amoure.suitmediatest.view.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amoure.suitmediatest.data.UserRepository
import com.amoure.suitmediatest.data.response.UserItem

class ThirdViewModel(userRepository: UserRepository) : ViewModel() {

    val users: LiveData<PagingData<UserItem>> =
        userRepository.getUsers().cachedIn(viewModelScope)

}