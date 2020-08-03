package com.juansebastian.pruebajuniorandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoDetallePropioPresenter
import org.w3c.dom.Text

class DetalleVehiculoPropioActivity : AppCompatActivity(), DetalleVehiculoPropioActivityInterface {
    private var vehiculoDetallePropioPresenter: VehiculoDetallePropioPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_vehiculo_propio)
        val marca: TextView = findViewById(R.id.marca_propio)
        val modelo: TextView = findViewById(R.id.modelo_propio)
        val favorito: TextView = findViewById(R.id.favorito_detalle_propio)
        val estado: TextView = findViewById(R.id.estado_propio)
        val coleccion: TextView = findViewById(R.id.coleccion_propio_detalle)
        val combustion: TextView = findViewById(R.id.combustion_detalle_propio)
        val ubicacion: TextView = findViewById(R.id.ubicacion_propio_detalle)
        val eliminacion: TextView = findViewById(R.id.eliminacion_propio_detalle)
        val imagenVehiculo: ImageView = findViewById(R.id.imagen_vehiculo_propio)
        vehiculoDetallePropioPresenter = VehiculoDetallePropioPresenter(applicationContext, this)

        mostrarDetalleVehiculo(marca, modelo, favorito, estado, coleccion, combustion, ubicacion, eliminacion, imagenVehiculo)

    }

    override fun mostrarDetalleVehiculo(marca: TextView, modelo: TextView, favorito: TextView, estado: TextView,
                               coleccion: TextView, combustion: TextView, ubicacion: TextView, eliminacion: TextView,
                               imagenVehiculo: ImageView) {

        vehiculoDetallePropioPresenter!!.mostrarDetalleVehiculo(marca, modelo, favorito, estado, coleccion,
                combustion, ubicacion, eliminacion, imagenVehiculo)
    }
}