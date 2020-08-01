package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo

class VehiculoDetallePropioPresenter(val context: Context): VehiculoDetallePropioInterface {



    override fun mostrarDetalleVehiculo(marca: TextView, modelo: TextView, favorito: TextView, estado: TextView,
                               coleccion: TextView, combustion: TextView, ubicacion: TextView, eliminacion: TextView,
                               imagenVehiculo: ImageView) {

        val preferencias: SharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

        if (!preferencias.getString("favorito_propio", "").equals("null")) {
            favorito.setText("Si")
        } else {
            favorito.setText("No")
        }

        if (preferencias.getString("eliminacion_propio", "").equals("Si")) {
            eliminacion.setText("Su vehículo se encuentra en proceso de eliminación")
        } else {
            eliminacion.setText("")
        }

        marca.setText(preferencias.getString("marca_propio", ""))
        modelo.setText(preferencias.getString("modelo_propio", ""))
        estado.setText(preferencias.getString("estado_propio", ""))
        coleccion.setText(preferencias.getString("coleccion_propio", ""))
        combustion.setText(preferencias.getString("combustion_propio", ""))
        ubicacion.setText(preferencias.getString("ubicacion_propio", ""))


    }


}