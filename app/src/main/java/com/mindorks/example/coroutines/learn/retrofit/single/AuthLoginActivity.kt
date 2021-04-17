package com.mindorks.example.coroutines.learn.retrofit.single

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mindorks.example.coroutines.*
import com.mindorks.example.coroutines.data.api.WebApi
import com.mindorks.example.coroutines.data.api.WebServiceResponse
import com.mindorks.example.coroutines.utils.AuthLoginViewModelFactory
import kotlinx.android.synthetic.main.activity_auth_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthLoginActivity : AppCompatActivity() {
    private lateinit var authLoginViewModel: AuthLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_login)
        setUpUi()
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpUi() {
        progressBar.visibility = GONE
        btSignIn.setOnClickListener {
            if(etUserName.text.toString().trim().length!=0&&etPwd.text.toString().trim().length!=0){
                GlobalScope.launch(Dispatchers.Main) {
                    progressBar.visibility = VISIBLE
                    authLoginViewModel.validateLogin(etUserName.text.toString().trim(),etPwd.text.toString().trim())

                }
            }else{
                Toast.makeText(this,"Please fill the required fields",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setUpObserver() {
        authLoginViewModel.getServerResponse().observe(this, Observer {
            progressBar.visibility = GONE
            if(it.result!=null){
                if (it.result!!.contains("SUCCESS ")){
                    Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this,"Invalid User Login",Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this,"Something went wrong,Please try again",Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun setUpViewModel() {
        val webApi = WebApi()
        val webServiceResponse = WebServiceResponse()
        authLoginViewModel = ViewModelProviders.of(this, AuthLoginViewModelFactory(webApi,webServiceResponse))
            .get(AuthLoginViewModel::class.java)

    }

    companion object {
        private const val TAG = "AuthLoginActivity"
    }

}
