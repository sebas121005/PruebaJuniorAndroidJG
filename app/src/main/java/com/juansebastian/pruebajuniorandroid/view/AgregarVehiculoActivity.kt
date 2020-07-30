package com.juansebastian.pruebajuniorandroid.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoAgregaPresenter

class AgregarVehiculoActivity : AppCompatActivity(), AgregarVehiculoActivityInterface {

    private var vehiculoAgregarPresenter: VehiculoAgregaPresenter? = null
    val REQUEST_IMAGE_CAPTURE = 1
    var imagenVehiculo: ImageView? = null
    private var preferencias: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_vehiculo)
        val editMarca: TextInputEditText = findViewById(R.id.edit_marca)
        val editModelo: TextInputEditText = findViewById(R.id.edit_modelo)
        val spinnerEstado: Spinner = findViewById<Spinner>(R.id.selecciona_estado)
        val spinnerFavorito: Spinner = findViewById<Spinner>(R.id.selecciona_favorito)
        val agregarVehiculo: Button = findViewById(R.id.guardar_vehiculo)
        val seleccionaImagen: ImageButton = findViewById(R.id.selecciona_imagen)
        val editColeccion: TextInputEditText = findViewById(R.id.edit_coleccion)
        val spinnerCombustion: Spinner = findViewById(R.id.selecciona_combustion)
        val tomarUbicacion: ImageButton = findViewById(R.id.tomar_ubicacion)
        val coordenada: TextView = findViewById(R.id.coordenada)
        val spinnerEliminacion: Spinner = findViewById(R.id.selecciona_eliminacion)
        imagenVehiculo = findViewById(R.id.imagen_vehiculo)
        preferencias = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

        vehiculoAgregarPresenter = VehiculoAgregaPresenter(applicationContext, this)

        llenarSpinner(spinnerEstado, spinnerFavorito, spinnerCombustion, spinnerEliminacion)

        agregarVehiculo.setOnClickListener { guardarVehiculo(editMarca, editModelo, spinnerEstado, spinnerFavorito, editColeccion,
                                                                spinnerCombustion, spinnerEliminacion, preferencias!!)  }
        seleccionaImagen.setOnClickListener { tomarImagen(REQUEST_IMAGE_CAPTURE) }
        tomarUbicacion.setOnClickListener { val intent: Intent = Intent(this, MapsActivity::class.java)
                                            startActivity(intent)}

    }

    override fun llenarSpinner(spinnerEstado: Spinner, spinnerFavorito: Spinner, spinnerCombustion: Spinner,
                               spinnerEliminacion: Spinner) {
        vehiculoAgregarPresenter!!.llenarSpinner(spinnerEstado, spinnerFavorito, spinnerCombustion, spinnerEliminacion)
    }

    override fun guardarVehiculo(editMarca: TextInputEditText, editModelo: TextInputEditText, spinnerEstado: Spinner,
                                 spinnerFavorito: Spinner, editColeccion: TextInputEditText, spinnerCombustion: Spinner,
                                 spinnerEliminacion: Spinner, preferencias: SharedPreferences) {
        vehiculoAgregarPresenter!!.guardarVehiculo(editMarca, editModelo, spinnerEstado, spinnerFavorito, editColeccion,
                                                    spinnerCombustion, spinnerEliminacion, preferencias)
    }

    override fun tomarImagen(REQUEST_IMAGE_CAPTURE: Int) {
        vehiculoAgregarPresenter!!.tomarImagen(REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        vehiculoAgregarPresenter?.guardarImagen(requestCode, resultCode, data, REQUEST_IMAGE_CAPTURE, imagenVehiculo)

    }

}