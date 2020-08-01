package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo

interface VehiculoDetallePropioInterface {


    fun mostrarDetalleVehiculo(marca: TextView, modelo: TextView, favorito: TextView, estado: TextView,
                               coleccion: TextView, combustion: TextView, ubicacion: TextView, eliminacion: TextView,
                               imagenVehiculo: ImageView)
}