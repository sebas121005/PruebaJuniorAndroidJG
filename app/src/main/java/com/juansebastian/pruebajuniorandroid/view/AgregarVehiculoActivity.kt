package com.juansebastian.pruebajuniorandroid.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoAgregaPresenter

class AgregarVehiculoActivity : AppCompatActivity(), AgregarVehiculoActivityInterface {

    private var vehiculoAgregarPresenter: VehiculoAgregaPresenter? = null
    val REQUEST_IMAGE_CAPTURE = 1
    var imagenVehiculo: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_vehiculo)
        val editMarca: TextInputEditText = findViewById(R.id.edit_marca)
        val editModelo: TextInputEditText = findViewById(R.id.edit_modelo)
        val spinnerEstado: Spinner = findViewById<Spinner>(R.id.selecciona_estado)
        val spinnerFavorito: Spinner = findViewById<Spinner>(R.id.selecciona_favorito)
        val agregarVehiculo: Button = findViewById(R.id.guardar_vehiculo)
        val selecciona_imagen: ImageButton = findViewById(R.id.selecciona_imagen)
        imagenVehiculo = findViewById(R.id.imagen_vehiculo)

        vehiculoAgregarPresenter = VehiculoAgregaPresenter(applicationContext, this)

        llenarSpinner(spinnerEstado, spinnerFavorito)

        agregarVehiculo.setOnClickListener { guardarVehiculo(editMarca, editModelo, spinnerEstado, spinnerFavorito)  }
        selecciona_imagen.setOnClickListener { tomarImagen(REQUEST_IMAGE_CAPTURE) }

    }

    override fun llenarSpinner(spinnerEstado: Spinner, spinnerFavorito: Spinner) {
        vehiculoAgregarPresenter!!.llenarSpinner(spinnerEstado, spinnerFavorito)
    }

    override fun guardarVehiculo(editMarca: TextInputEditText, editModelo: TextInputEditText, spinnerEstado: Spinner,
                                 spinnerFavorito: Spinner) {
        vehiculoAgregarPresenter!!.guardarVehiculo(editMarca, editModelo, spinnerEstado, spinnerFavorito)
    }

    override fun tomarImagen(REQUEST_IMAGE_CAPTURE: Int) {
        vehiculoAgregarPresenter!!.tomarImagen(REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        vehiculoAgregarPresenter?.guardarImagen(requestCode, resultCode, data, REQUEST_IMAGE_CAPTURE, imagenVehiculo)

    }

}