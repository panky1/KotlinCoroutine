package com.mindorks.example.coroutines.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindorks.example.coroutines.data.api.WebApi
import com.mindorks.example.coroutines.data.api.WebServiceResponse
import com.mindorks.example.coroutines.learn.retrofit.single.AuthLoginViewModel

class AuthLoginViewModelFactory(private val webApi: WebApi, private val webServiceResponse: WebServiceResponse) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthLoginViewModel::class.java)) {
            return AuthLoginViewModel(webApi,webServiceResponse) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}