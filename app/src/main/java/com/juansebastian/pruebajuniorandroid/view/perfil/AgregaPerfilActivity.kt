package com.juansebastian.pruebajuniorandroid.view.perfil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.perfil.PerfilAgregaPresenter

class AgregaPerfilActivity : AppCompatActivity(), AgregaPerfilActivityInterface {
    private var agregaPerfilPresenter: PerfilAgregaPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agrega_perfil)
        val nombrePerfil: TextView = findViewById(R.id.nombre_perfil_agrega)
        val contrasenaPerfil: TextView = findViewById(R.id.contrasena_perfil_agrega)
        val direccion: TextView = findViewById(R.id.ubicacion_perfil_agrega)
        val tomarUbicacion: ImageButton = findViewById(R.id.tomar_ubicacion_perfila_agrega)
        val agregaPerfil: Button = findViewById(R.id.guarda_perfil)
        agregaPerfilPresenter = PerfilAgregaPresenter(applicationContext, this)

        mostrarDetalle(nombrePerfil, contrasenaPerfil, direccion, tomarUbicacion, agregaPerfil)

    }

    override fun mostrarDetalle(nombre: TextView, contrasena: TextView, ubicacion: TextView,
                                tomarUbicacion: ImageButton, guardarPerfil: Button) {
        agregaPerfilPresenter!!.mostrarDetalle(nombre, contrasena, ubicacion, tomarUbicacion, guardarPerfil)
    }
}