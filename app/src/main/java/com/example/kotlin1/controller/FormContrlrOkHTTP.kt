package com.example.kotlin1.controller

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1.R
import com.example.kotlin1.model.services.Network
import com.example.kotlin1.model.services.okhttp.OkHTTPService

object FormContrlrOkHTTP {

    fun sendData(app: AppCompatActivity) {
        try {
            OkHTTPService().getData("https://www.google.com",app)
        } catch (err: Network.minSdkException) {
            throw Exception(app.getString(R.string.err_networkSDK))
        } catch (err: Network.NoNetworkException) {
            throw Exception(app.getString(R.string.err_networkNotAvailable))
        }catch(ex:Exception){
            Log.d("CONTROLLER",ex.message)
        }finally {
            Log.d("CONTROLLER","SE OCULTO")
        }
    }

}