package com.mindorks.example.coroutines

import android.app.Activity
import android.app.DatePickerDialog

class WebService {
    private lateinit var DATA: String
    private lateinit var METHOD: String
    private lateinit var webCall: WebCall
    private var activity:Activity?= null

    fun setActivity(activity: Activity){
        this.activity = activity
    }

    fun setResponseListener(webCall: WebCall){
        this.webCall = webCall
    }

    fun startToHitService(){

    }

    fun setMETHOD(METHOD: String){
        this.METHOD = METHOD
    }

    fun setDATA(DATA:String){
        this.DATA = DATA
    }


}