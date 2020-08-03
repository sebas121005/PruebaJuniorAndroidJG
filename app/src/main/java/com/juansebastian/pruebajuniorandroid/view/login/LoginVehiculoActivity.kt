package com.juansebastian.pruebajuniorandroid.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.juansebastian.pruebajuniorandroid.MainActivity
import com.juansebastian.pruebajuniorandroid.R

class LoginVehiculoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_vehiculo)
        val inicioSesion: Button = findViewById(R.id.inicio_sesion)

        inicioSesion.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}