package com.example.kotlin1.controller

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1.R
import com.example.kotlin1.model.beans.User
import com.example.kotlin1.model.services.native_http.HttpAsyncService
import com.example.kotlin1.model.services.native_http.interfaces.ICompleteListener
import com.example.kotlin1.model.services.Network
import com.example.kotlin1.view.custom.LoadingDialog


object FormContrlr:ICompleteListener {

    fun sendData(user: User, app: AppCompatActivity) {
        try {
            LoadingDialog.show(app)
            HttpAsyncService(this).execute("https://www.google.com")
        } catch (err: Network.minSdkException) {
            throw Exception(app.getString(R.string.err_networkSDK))
        } catch (err: Network.NoNetworkException) {
            throw Exception(app.getString(R.string.err_networkNotAvailable))
        }catch(ex:Exception){
            Log.d("CONTROLLER",ex.message)
            LoadingDialog.hide()
        }
    }

    override fun onComplete(result: String) {
        Log.d("Controller",result)
        Log.d("Controller",HttpAsyncService(this).status.toString())
        LoadingDialog.hide()
    }

}