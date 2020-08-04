package com.juansebastian.pruebajuniorandroid.presenter.perfil

import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

interface PerfilAgregaPresenterInterface {

    fun mostrarDetalle(nombre: TextView, contrasena: TextView, ubicacion: TextView,
                       tomarUbicacion: ImageButton, guardarPerfil: Button)
}