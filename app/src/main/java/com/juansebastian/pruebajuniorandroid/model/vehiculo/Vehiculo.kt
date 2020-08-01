package com.juansebastian.pruebajuniorandroid.model.vehiculo

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoDisponibleInterface
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoPropioInterface


class Vehiculo: VehiculoInterface {
    private var marca: String? = null
    private var modelo: String? = null
    private var solictudEliminacion: String? = null
    private var estado: String? = null
    private var favorito: Drawable? = null
    private var imagen: Bitmap? = null
    private var ubicacion: String? = null
    private var coleccion: String? = null
    private var tipoCombustion: String? = null
    private var vehiculoPropioPresenterInterface: VehiculoPropioInterface? = null
    private var vehiculoDisponiblePresenterInterface: VehiculoDisponibleInterface? = null

    constructor(marca: String, modelo: String, imagen: Bitmap?, favorito: Drawable?, solicitud: String?,
                estado: String?, ubicacion: String?, coleccion: String?, tipoCombustion: String?) {
        this.marca = marca
        this.modelo = modelo
        this.favorito = favorito
        this.imagen = imagen
        this.solictudEliminacion = solicitud
        this.estado = estado
        this.ubicacion = ubicacion
        this.coleccion = coleccion
        this.tipoCombustion = tipoCombustion

    }

    constructor(presenterInterface: VehiculoPropioInterface) {
        this.vehiculoPropioPresenterInterface = presenterInterface
    }

    constructor(disponibleInterface: VehiculoDisponibleInterface) {
        this.vehiculoDisponiblePresenterInterface = disponibleInterface
    }


    override fun getMarca(): String {
        return marca!!
    }

    override fun setMarca(marca: String) {
        this.marca = marca
    }

    override fun getModelo(): String {
        return modelo!!
    }

    override fun setModelo(modelo: String) {
        this.marca = modelo
    }

    override fun getSolicitud(): String {
        return solictudEliminacion!!
    }

    override fun setSolicitud(solicitud: String) {
        this.solictudEliminacion = solicitud
    }

    override fun getEstado(): String {
        return estado!!
    }

    override fun setEstado(estado: String) {
        this.estado = estado
    }

    override fun getFavorito(): Drawable? {
        return favorito
    }

    override fun setFavorito(favorito: Drawable) {
        this.favorito = favorito
    }

    override fun getUbicacion(): String {
        return ubicacion!!
    }

    override fun setUbicacion(ubicacion: String) {
        this.ubicacion = ubicacion
    }

    override fun getColeccion(): String {
        return coleccion!!
    }

    override fun setColeccion(coleccion: String) {
        this.coleccion = coleccion
    }

    override fun getCombustion(): String {
        return tipoCombustion!!
    }

    override fun setCombustion(combustion: String) {
        this.tipoCombustion = tipoCombustion
    }

    override fun getImagen(): Bitmap? {
        return imagen
    }

    override fun setImagen(imagen: Bitmap) {
        this.imagen = imagen
    }

    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        return (itemsAgregar)
    }

}
