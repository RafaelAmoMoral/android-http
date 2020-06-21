package com.example.kotlin1.model.services.volley

import android.app.DownloadManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VolleyService {

    fun getData(url: String, app: AppCompatActivity) {
        val queue = Volley.newRequestQueue(app)
        val request = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                try {
                  Log.d("VOLLEYSERVICE",response)
                }catch (ex:Exception){ }
            },
            Response.ErrorListener { err ->

            }
        )
        queue.add(request)
    }

}