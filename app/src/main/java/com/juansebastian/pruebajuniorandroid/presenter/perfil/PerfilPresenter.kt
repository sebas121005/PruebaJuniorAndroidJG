package com.juansebastian.pruebajuniorandroid.presenter.perfil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.model.perfil.Perfil
import com.juansebastian.pruebajuniorandroid.model.perfil.PerfilInterface
import com.juansebastian.pruebajuniorandroid.view.MapsActivity
import com.squareup.picasso.Picasso

class PerfilPresenter(val context: Context, val activity: Activity): PerfilPresenterInterface {
    private val perfilInterface: PerfilInterface = Perfil(this)

    override fun mostrarDetalle(cc: TextView, nombre: TextView, contrasena: TextView, imagenPerfil: ImageView,
                            ubicacion: TextView, tomarUbicacion: ImageButton) {
        val preferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val perfil =  Perfil("Juan", "123", "https://picsum.photos/500/500", "", "456")

        cc.setText(perfil.getCc())
        nombre.setText(perfil.getNombre())
        contrasena.setText(perfil.getContrasena())

        Picasso.get().load(perfil.getImagen()).into(imagenPerfil)
        ubicacion.setText(preferences.getString("ubicacion_perfil", ""))

        tomarUbicacion.setOnClickListener(View.OnClickListener {

            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("determina_ubicacion", "1")
            editor.commit()
            val intent: Intent = Intent(activity, MapsActivity::class.java)
            activity.startActivity(intent)
        })

    }
}