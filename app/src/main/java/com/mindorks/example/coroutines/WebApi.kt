package com.mindorks.example.coroutines

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.MarshalFloat
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.lang.Exception

class WebApi {
    companion object{
        val LOGIN ="UserLogin"
        val WSDL_TARGET_NAMESPACE = "http://tempuri.org/"
    }

    private lateinit var httpTransportSE: HttpTransportSE

    var getUrl:String?=null

   suspend fun getLoginData(userid:String,password:String):String{
        val soapObject = SoapObject(WSDL_TARGET_NAMESPACE, LOGIN)
        soapObject.addProperty("strPlant","")
        soapObject.addProperty("strUser",userid)
        soapObject.addProperty("strPass",password)
        return callServer(soapObject, LOGIN)
    }

    suspend fun callServer(soapObject: SoapObject, method: String): String {

            val soapSerializationEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapSerializationEnvelope.dotNet = true
            soapSerializationEnvelope.implicitTypes = true
            soapSerializationEnvelope.encodingStyle = SoapSerializationEnvelope.XSD;
            soapSerializationEnvelope.setOutputSoapObject(soapObject)
            getUrl = "http://192.168.1.101/MobivueWmsWs/SightWebService.asmx"
        try{
         httpTransportSE = HttpTransportSE(getUrl,30000)
            httpTransportSE.debug = true
            httpTransportSE.call(WSDL_TARGET_NAMESPACE+method,soapSerializationEnvelope)
            return soapSerializationEnvelope.getResponse().toString()
        }catch (ex:Exception){
            return ex.toString()
        }

    }
}