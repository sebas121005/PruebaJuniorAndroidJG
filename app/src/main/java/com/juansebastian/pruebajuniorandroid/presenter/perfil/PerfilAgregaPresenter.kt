package com.juansebastian.pruebajuniorandroid.presenter.perfil

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.MainActivity
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.DbVehiculo
import com.juansebastian.pruebajuniorandroid.view.MapsActivity
import org.json.JSONObject

class PerfilAgregaPresenter(val context: Context, val activity: Activity): PerfilAgregaPresenterInterface {

    override fun mostrarDetalle(nombre: TextView, contrasena: TextView, ubicacion:TextView,
                                tomarUbicacion: ImageButton, guardarPerfil: Button) {
        val preferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

        if (!preferences.getString("agrega_perfil", "").equals("1")) {

            nombre.setText(preferences.getString("usuario", ""))
            contrasena.setText(preferences.getString("contrasena", ""))

            val editor: SharedPreferences.Editor = preferences.edit()

            tomarUbicacion.setOnClickListener(View.OnClickListener {

                editor.putString("determina_ubicacion", "1")
                editor.commit()
                val intent: Intent = Intent(activity, MapsActivity::class.java)
                activity.startActivity(intent)
            })

            guardarPerfil.setOnClickListener(View.OnClickListener {
                val dataUbicacion: JSONObject = JSONObject(preferences.getString("ubicacion_perfil", ""))

                val dbVehiculo = DbVehiculo(context)
                val db = dbVehiculo.writableDatabase


                val values = ContentValues().apply {
                    put(DbVehiculo.Perfil.COLUMN_NAME_USUARIO, preferences.getString("usuario", ""))
                    put(DbVehiculo.Perfil.COLUMN_NAME_DATA, "$dataUbicacion")
                }

                val nuevaFila = db?.insert("PERFIL", null, values)

                editor.putString("agrega_perfil", "1")
                editor.commit()

                val intent: Intent = Intent(activity, MainActivity::class.java)
                activity.startActivity(intent)
            })
        } else {
            val intent: Intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }



    }
}