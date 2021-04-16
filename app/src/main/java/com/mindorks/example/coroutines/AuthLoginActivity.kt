package com.mindorks.example.coroutines

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_auth_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthLoginActivity : AppCompatActivity() {
    private lateinit var webServiceResponse: WebServiceResponse
    private lateinit var webApi: WebApi
    private lateinit var authLoginViewModel: AuthLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_login)
        setUpUi()
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpUi() {
        btSignIn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                webApi = WebApi()
                webServiceResponse = WebServiceResponse();
                authLoginViewModel.validateLogin(webApi, webServiceResponse)

            }
        }
    }

    private fun setUpObserver() {
        authLoginViewModel.getServerResponse().observe(this, Observer {
            Log.d(TAG, "setUpObserver:" + it.result)
        })
    }

    private fun setUpViewModel() {
        val webService = WebService();
        authLoginViewModel = ViewModelProviders.of(this, AuthLoginViewModelFactory(webService))
            .get(AuthLoginViewModel::class.java)

    }

    companion object {
        private const val TAG = "AuthLoginActivity"
    }

}
