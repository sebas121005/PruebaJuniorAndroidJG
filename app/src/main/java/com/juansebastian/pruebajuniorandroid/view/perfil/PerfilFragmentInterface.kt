package com.juansebastian.pruebajuniorandroid.view.perfil

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

interface PerfilFragmentInterface {
    fun mostrarDetalle(nombre: TextView, contrasena: TextView, imagenPerfil: ImageView,
                        ubicacion: TextView, tomarUbicacion: ImageButton)
}