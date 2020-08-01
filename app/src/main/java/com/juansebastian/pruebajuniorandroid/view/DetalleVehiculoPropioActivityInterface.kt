package com.juansebastian.pruebajuniorandroid.view

import android.widget.ImageView
import android.widget.TextView

interface DetalleVehiculoPropioActivityInterface {
    fun mostrarDetalleVehiculo(marca: TextView, modelo: TextView, favorito: TextView, estado: TextView,
                               coleccion: TextView, combustion: TextView, ubicacion: TextView, eliminacion: TextView,
                               imagenVehiculo: ImageView)
}