package com.juansebastian.pruebajuniorandroid.presenter.perfil

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

interface PerfilPresenterInterface {

    fun mostrarDetalle(cc: TextView, nombre: TextView, contrasena: TextView, imagenPerfil: ImageView,
                       ubicacion: TextView, tomarUbicacion: ImageButton)
}