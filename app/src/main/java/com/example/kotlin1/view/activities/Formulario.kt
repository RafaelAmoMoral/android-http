package com.example.kotlin1.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.kotlin1.R
import com.example.kotlin1.controller.FormContrlr
import com.example.kotlin1.controller.FormContrlrOkHTTP
import com.example.kotlin1.controller.FormVolleyController
import com.example.kotlin1.model.beans.User
import kotlinx.android.synthetic.main.activity_formulario.*


class Formulario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        //Obtención de la barra de navegación, si existe
        val actionbar: ActionBar? = supportActionBar
        //Si existe, habilitamos el botón hacia atrás
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Handle del back button de la actividad
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
    }

    fun onSendClicked(view: View) {
        try {
            val userName: String = user_txtInputTxt.text.toString().trim()
            val email: String = email_txtInputTxt.text.toString().trim()
            val password: String = password_txtInputTxt.text.toString().trim()

            val user = User(null, userName, email, password)
            FormContrlrOkHTTP.sendData(this)

        } catch (err: Exception) {
            Toast.makeText(this, err.message, Toast.LENGTH_SHORT).show()
        }
    }

}