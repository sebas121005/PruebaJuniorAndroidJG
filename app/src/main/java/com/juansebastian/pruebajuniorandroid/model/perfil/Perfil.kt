package com.juansebastian.pruebajuniorandroid.model.perfil

import com.juansebastian.pruebajuniorandroid.presenter.perfil.PerfilPresenterInterface

class Perfil: PerfilInterface {
    private var nombre: String? = null
    private var cc: String? = null
    private var contrasena: String? = null
    private var imagen: String? = null
    private var ubicacion: String? = null

    private var perfilPresenterInterface: PerfilPresenterInterface? = null

    constructor(nombre: String?, cc: String?, imagen: String?, ubicacion: String?, contrasena: String) {
        this.nombre = nombre
        this.cc = cc
        this.imagen = imagen
        this.ubicacion = ubicacion
        this.contrasena = contrasena
    }

    constructor(perfilPresenterInterface: PerfilPresenterInterface) {
        this.perfilPresenterInterface = perfilPresenterInterface
    }

    override fun getNombre(): String? {
        return this.nombre
    }

    override fun setNombre(nombre: String?) {
        this.nombre = nombre
    }

    override fun getCc(): String? {
        return this.cc
    }

    override fun setCc(cc: String?) {
        this.cc = cc
    }

    override fun getImagen(): String? {
        return this.imagen
    }

    override fun setImagen(imagen: String?) {
        this.imagen = imagen
    }

    override fun getUbicacion(): String? {
        return this.ubicacion
    }

    override fun setUbicacion(ubicacion: String?) {
        this.ubicacion = ubicacion
    }

    override fun getContrasena(): String? {
        return this.contrasena
    }

    override fun setContrasena(contrasena: String?) {
        this.contrasena = contrasena
    }


}