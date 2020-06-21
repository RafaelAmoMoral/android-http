package com.example.kotlin1.controller

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1.R
import com.example.kotlin1.model.beans.User
import com.example.kotlin1.model.services.Network
import com.example.kotlin1.model.services.volley.VolleyService
import com.example.kotlin1.view.custom.LoadingDialog

object FormVolleyController {

    fun sendData(user: User, app: AppCompatActivity) {
        try {
            LoadingDialog.show(app)
            VolleyService().getData("https://www.facebook.com",app)
        } catch (err: Network.minSdkException) {
            throw Exception(app.getString(R.string.err_networkSDK))
        } catch (err: Network.NoNetworkException) {
            throw Exception(app.getString(R.string.err_networkNotAvailable))
        }catch(ex:Exception){
            Log.d("CONTROLLER",ex.message)
        }finally {
            LoadingDialog.hide()
        }
    }

}