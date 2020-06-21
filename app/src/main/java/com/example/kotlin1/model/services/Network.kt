package com.example.kotlin1.model.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import kotlin.Error

class Network {

    companion object {

        //Necesita permiso ACCESS NETWORK STATE e INTERNET
        @Throws (NoNetworkException::class , minSdkException::class)
        fun isConnectionAvailable(activity: AppCompatActivity): Boolean {
            val connectivityManager =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkInfo =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                val isConnected: Boolean? =
                    networkInfo?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

                return networkInfo != null && isConnected == true

            } else {
                throw minSdkException("SDK-ERROR")
            }
        }
    }

    class NoNetworkException(message: String) : Exception(message)
    class minSdkException(message: String) : Exception(message)



}

