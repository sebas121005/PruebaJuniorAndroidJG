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
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.DbVehiculo
import com.juansebastian.pruebajuniorandroid.view.MapsActivity
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.util.ArrayList

class PerfilPresenter(val context: Context, val activity: Activity): PerfilPresenterInterface {
    private val perfilInterface: PerfilInterface = Perfil(this)

    override fun mostrarDetalle(nombre: TextView, contrasena: TextView, imagenPerfil: ImageView,
                            ubicacion: TextView, tomarUbicacion: ImageButton) {
        val preferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

        val dbVehiculo = DbVehiculo(context)
        val db = dbVehiculo.readableDatabase
        val projection = arrayOf("rowid", DbVehiculo.Perfil.COLUMN_NAME_USUARIO, DbVehiculo.Perfil.COLUMN_NAME_DATA)
        val selection = "${DbVehiculo.Perfil.COLUMN_NAME_USUARIO} = ?"
        val selectionArgs = arrayOf(preferences.getString("usuario", ""))

        val sortOrder = "${"rowid"} DESC"

        val cursor = db.query(
                DbVehiculo.Perfil.TABLE_NAME_PERFIL,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )

        var dataJson: JSONObject? = null


        if (cursor!= null) {
            with(cursor) {
                while (moveToNext()) {
                    dataJson = JSONObject(cursor.getString(2))
                    val perfil =  Perfil(dataJson!!.getString("usuario"), "https://picsum.photos/500/500",
                            dataJson!!.getString("ubicacion"), dataJson!!.getString("contrasena"))

                    nombre.setText(perfil.getNombre())
                    contrasena.setText(perfil.getContrasena())

                    Picasso.get().load(perfil.getImagen()).into(imagenPerfil)
                    ubicacion.setText(perfil.getUbicacion())

                }
            }

        }


        tomarUbicacion.setOnClickListener(View.OnClickListener {

            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("determina_ubicacion", "1")
            editor.commit()

            val intent: Intent = Intent(activity, MapsActivity::class.java)
            activity.startActivity(intent)
        })

    }
}