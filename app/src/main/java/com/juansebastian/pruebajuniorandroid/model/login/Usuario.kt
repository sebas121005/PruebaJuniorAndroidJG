package com.juansebastian.pruebajuniorandroid.model.login

class Usuario: UsuarioInterface {

    private var usuario: String? = null
    private var contrasena: String? = null

    constructor(usuario: String, contrasena: String) {
        this.usuario = usuario
        this.contrasena = contrasena
    }

    override fun getUsuario(): String? {
        return this.usuario
    }

    override fun setUsuario(usuario: String?) {
        this.usuario = usuario
    }

    override fun getContrasena(): String? {
        return this.contrasena
    }

    override fun setContrasena(contrasena: String?) {
        this.contrasena = contrasena
    }
}