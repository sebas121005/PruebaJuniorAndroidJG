package com.juansebastian.pruebajuniorandroid.view

import android.content.SharedPreferences
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText

interface AgregarVehiculoActivityInterface {

    fun llenarSpinner(spinnerEstado: Spinner, spinnerFavorito: Spinner, spinnerCombustion: Spinner, spinnerEliminacio: Spinner)

    fun guardarVehiculo(editMarca: TextInputEditText, editModelo: TextInputEditText, spinnerEstado: Spinner,
                        spinnerFavorito: Spinner, editColeccion: TextInputEditText, spinnerCombustion: Spinner,
                        spinnerEliminacion: Spinner, preferencias: SharedPreferences)

    fun tomarImagen(REQUEST_IMAGE_CAPTURE: Int)
}