package com.mindorks.example.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindorks.example.coroutines.learn.retrofit.single.SingleNetworkCallViewModel
import com.mindorks.example.coroutines.utils.ViewModelFactory

class AuthLoginViewModelFactory(private val webService: WebService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthLoginViewModel::class.java)) {
            return AuthLoginViewModel(webService) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}