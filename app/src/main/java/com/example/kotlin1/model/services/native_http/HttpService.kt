package com.example.kotlin1.model.services.native_http

import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin1.model.beans.User
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


object HttpService {

    fun get(user: User, app: AppCompatActivity) {
        // Instantiate the RequestQueue.

        Log.d("TEST", user.toString())

        val queue = Volley.newRequestQueue(app.applicationContext)
        val url = "http://www.google.com"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                Log.d("response", response)
            },
            Response.ErrorListener { err: VolleyError ->
                Log.d("err", err.localizedMessage)
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    @Throws(IOException::class)
    fun nativeSynchronus(app: AppCompatActivity) {
        //Hay que tener en cuenta que al tratarse de peticiones syncronas es el propio Android
        // quien lanza una petición, ya que el hilo principal es el de la interfaz.
        //Si eliminamos las siguientes dos líneas saltará una excepción (ver el catch)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var inputStream: InputStream? = null

        try {
            val url = URL("https://www.google.com")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            return inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (ex: Exception) {
            Log.d("HTTPSERVICE", ex.toString())
            throw ex
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }

}