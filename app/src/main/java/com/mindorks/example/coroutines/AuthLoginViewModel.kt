package com.mindorks.example.coroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthLoginViewModel(
    private val webService: WebService
    ) : ViewModel() {

    private lateinit var webServiceResponse: WebServiceResponse
    private lateinit var webApi: WebApi

    private val mutableLiveData = MutableLiveData<WebServiceResponse>()

   /* init {
        webApi = WebApi()
        webServiceResponse = WebServiceResponse();
        validateLogin(webApi,webServiceResponse)
    }*/

    fun validateLogin(webApi: WebApi, webServiceResponse: WebServiceResponse) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val serverResponse =  webApi.getLoginData("BCIL","123")
                webServiceResponse.result = serverResponse
                mutableLiveData.postValue(webServiceResponse)
            }

        }
    }

    fun getServerResponse():MutableLiveData<WebServiceResponse>{
        return mutableLiveData
    }
}