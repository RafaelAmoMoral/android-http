package com.example.kotlin1.model.services.okhttp

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1.view.custom.LoadingDialog
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class OkHTTPService {

    fun getData(url: String, app: AppCompatActivity) {

        LoadingDialog.show(app)

        val client = OkHttpClient()
        val request = okhttp3.Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {

            override fun onResponse(call: Call, response: Response) {
                app.runOnUiThread {
                    Log.d("OkHTTPService", response.body.toString())
                    LoadingDialog.hide()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                LoadingDialog.hide()
            }

        })

    }

}