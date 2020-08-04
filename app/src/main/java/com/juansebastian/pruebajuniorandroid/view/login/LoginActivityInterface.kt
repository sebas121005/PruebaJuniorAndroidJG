package com.juansebastian.pruebajuniorandroid.view.login

import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

interface LoginActivityInterface {

    fun obtenerUsuario(usuario: TextInputEditText, contrasena: TextInputEditText, iniciarSesion: Button)
}