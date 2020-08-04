package com.juansebastian.pruebajuniorandroid.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.juansebastian.pruebajuniorandroid.MainActivity
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.login.UsuarioPresenter

class LoginVehiculoActivity : AppCompatActivity(), LoginActivityInterface {
    private var usuarioPresenter: UsuarioPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_vehiculo)
        val usuario: TextInputEditText = findViewById(R.id.usuario_login)
        val contrasena: TextInputEditText = findViewById(R.id.contrasena_login)
        val inicioSesion: Button = findViewById(R.id.inicio_sesion)
        usuarioPresenter = UsuarioPresenter(applicationContext, this)

        obtenerUsuario(usuario, contrasena, inicioSesion)

    }

    override fun obtenerUsuario(usuario: TextInputEditText, contrasena: TextInputEditText, iniciarSesion: Button) {
        usuarioPresenter!!.obtenerUsuario(usuario, contrasena, iniciarSesion)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}