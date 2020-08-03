package com.juansebastian.pruebajuniorandroid.model.perfil

interface PerfilInterface {

    fun getNombre(): String?

    fun setNombre(nombre: String?)

    fun getCc(): String?

    fun setCc(cc: String?)

    fun getImagen(): String?

    fun setImagen(imagen: String?)

    fun getUbicacion(): String?

    fun setUbicacion(ubicacion: String?)

    fun getContrasena(): String?

    fun setContrasena(contrasena: String?)
}