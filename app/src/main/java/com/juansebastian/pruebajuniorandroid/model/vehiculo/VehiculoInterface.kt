package com.juansebastian.pruebajuniorandroid.model.vehiculo

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import java.util.ArrayList

interface VehiculoInterface {

    fun getMarca(): String

    fun setMarca(marca: String)

    fun getModelo(): String

    fun setModelo(modelo: String)

    fun getSolicitud(): String

    fun setSolicitud(solicitudEliminacion: String)

    fun getEstado(): String

    fun setEstado(estado: String)

    fun getFavorito(): Drawable?

    fun setFavorito(favorito: Drawable)

    fun getUbicacion(): String

    fun setUbicacion(ubicacion: String)

    fun getColeccion(): String

    fun setColeccion(coleccion: String)

    fun getCombustion(): String

    fun setCombustion(combustion: String)

    fun getImagen(): Bitmap?

    fun setImagen(imagen: Bitmap)

    fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>?
}