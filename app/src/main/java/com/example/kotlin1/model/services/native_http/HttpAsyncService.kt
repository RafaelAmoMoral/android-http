package com.example.kotlin1.model.services.native_http

import android.os.AsyncTask
import android.util.Log
import com.example.kotlin1.model.services.native_http.interfaces.ICompleteListener
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class HttpAsyncService(private var completeListener:ICompleteListener?):AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String): String? {
        try {
            return getData(
                params[0]
            )
        } catch (IOE: IOException) {
            throw IOE
        }
    }

    override fun onPostExecute(result: String) {
        try {
            completeListener?.onComplete(result)
        }catch (ex:Exception){
            throw ex
        }
    }

    @Throws(IOException::class)
    fun getData(url: String): String {
        var inputStream: InputStream? = null

        try {
            val url = URL(url)
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
