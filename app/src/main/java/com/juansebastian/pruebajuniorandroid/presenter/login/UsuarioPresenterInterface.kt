package com.juansebastian.pruebajuniorandroid.presenter.login

import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

interface UsuarioPresenterInterface {

    fun obtenerUsuario(usuario: TextInputEditText, contrasena: TextInputEditText, iniciarSesion: Button)
}