package com.juansebastian.pruebajuniorandroid.view.perfil

import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

interface AgregaPerfilActivityInterface {

    fun mostrarDetalle(nombre: TextView, contrasena: TextView, ubicacion: TextView, tomarUbicacion: ImageButton,
                       guardarPerfil: Button)
}