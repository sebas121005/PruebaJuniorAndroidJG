package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.content.Intent
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText

interface VehiculoAgregarInterface {

    fun llenarSpinner(spinnerEstado: Spinner, spinnerFavorito: Spinner, spinnerCombustion: Spinner, spinnerEliminacio: Spinner)

    fun guardarVehiculo(editMarca: TextInputEditText, editModelo: TextInputEditText, spinnerEstado: Spinner,
                        spinnerFavorito: Spinner, editColeccion: TextInputEditText, spinnerCombustion: Spinner,
                        spinnerEliminacion: Spinner, preferencias: SharedPreferences)

    fun tomarImagen(REQUEST_IMAGE_CAPTURE: Int)

    fun guardarImagen(requestCode: Int, resultCode: Int, data: Intent?, REQUEST_IMAGE_CAPTURE: Int,
                      imagenVehiculo: ImageView?)
}