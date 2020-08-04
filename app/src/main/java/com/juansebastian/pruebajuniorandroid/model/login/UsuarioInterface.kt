package com.juansebastian.pruebajuniorandroid.model.login

interface UsuarioInterface {

    fun getUsuario(): String?

    fun setUsuario(usuario: String?)

    fun getContrasena(): String?

    fun setContrasena(contrasena: String?)
}