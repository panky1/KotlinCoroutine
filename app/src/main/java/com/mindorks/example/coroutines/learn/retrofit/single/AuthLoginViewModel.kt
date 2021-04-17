package com.mindorks.example.coroutines.learn.retrofit.single

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.example.coroutines.data.api.WebApi
import com.mindorks.example.coroutines.data.api.WebServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthLoginViewModel(
    private val webApi: WebApi,
    private val webServiceResponse: WebServiceResponse
) : ViewModel() {


    private val mutableLiveData = MutableLiveData<WebServiceResponse>()


    fun validateLogin(userId: String, pwd: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val serverResponse =  webApi.getLoginData(userId, pwd)
                if(serverResponse.contains("java.net")||serverResponse.contains("HTTP request failed")||serverResponse.contains("Failed")||serverResponse.contains("java.io")||serverResponse.contains("SoapException")){
                    webServiceResponse.errorMsg = "Something went wrong,please try again"
                    webServiceResponse.result = null
                }else{
                    webServiceResponse.result = serverResponse
                    webServiceResponse.errorMsg = null
                }

                mutableLiveData.postValue(webServiceResponse)
            }

        }
    }

    fun getServerResponse():MutableLiveData<WebServiceResponse>{
        return mutableLiveData
    }
}