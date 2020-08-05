package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.R
import com.squareup.picasso.Picasso


class VehiculoDetallePropioPresenter(val context: Context, val activity: Activity): VehiculoDetallePropioInterface {



    override fun mostrarDetalleVehiculo(marca: TextView, modelo: TextView, favorito: TextView, estado: TextView,
                               coleccion: TextView, combustion: TextView, ubicacion: TextView, eliminacion: TextView,
                               imagenVehiculo: ImageView) {

        val preferencias: SharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

        /**
         * Se valida y se muestra el detalle de los vehiculos disponibles
         */
        if (preferencias.getString("tipo_vehiculo", "").equals("disponible")) {

            if (!preferencias.getString("favorito_disponible", "").equals("null")) {
                favorito.setText("Si")
            } else {
                favorito.setText("No")
            }

            if (preferencias.getString("eliminacion_disponible", "").equals("true")) {
                eliminacion.setText("Su vehículo se encuentra en proceso de eliminación")
            } else {
                eliminacion.setText("")
            }

            marca.setText(preferencias.getString("marca_disponible", ""))
            modelo.setText(preferencias.getString("modelo_disponible", ""))
            estado.setText(preferencias.getString("estado_disponible", ""))
            coleccion.setText(preferencias.getString("combustion_disponible", ""))
            combustion.setText(preferencias.getString("combustion_disponible", ""))
            val textoString: String = "<p style=\"color:blue\"><u>${preferencias.getString("ubicacion_disponible", "")}</u></p>"
            val ubicacionEstilo: Spanned = Html.fromHtml(textoString, FROM_HTML_MODE_LEGACY)
            ubicacion.setText(ubicacionEstilo)

            Picasso.get().load(preferencias.getString("imagen_disponible", "")).into(imagenVehiculo)



            ubicacion.setOnClickListener(View.OnClickListener {
                val gmmIntentUri = Uri.parse("google.navigation:q=${preferencias.getString("ubicacion_disponible", "")}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                mapIntent.resolveActivity(context.packageManager)?.let {
                    activity.startActivity(mapIntent)
                }
            })


        }
        /**
         * De lo contrario se valida y se muestra el detalle de los vehiculos propios
         */
        else {
            if (!preferencias.getString("favorito_propio", "").equals("null")) {
                favorito.setText("Si")
            } else {
                favorito.setText("No")
            }

            if (preferencias.getString("eliminacion_propio", "").equals("Si")) {
                eliminacion.setText("Su vehículo se encuentra en proceso de eliminación")
            } else {
                eliminacion.setText("")
            }

            marca.setText(preferencias.getString("marca_propio", ""))
            modelo.setText(preferencias.getString("modelo_propio", ""))
            estado.setText(preferencias.getString("estado_propio", ""))
            coleccion.setText(preferencias.getString("coleccion_propio", ""))
            combustion.setText(preferencias.getString("combustion_propio", ""))
            val textoString: String = "<p style=\"color:blue\"><u>${preferencias.getString("ubicacion_propio", "")}</u></p>"
            val ubicacionEstilo: Spanned = Html.fromHtml(textoString, FROM_HTML_MODE_LEGACY)
            ubicacion.setText(ubicacionEstilo)
            Picasso.get().load(preferencias.getString("imagen_propio", "")).into(imagenVehiculo)


            ubicacion.setOnClickListener(View.OnClickListener {
                val gmmIntentUri = Uri.parse("google.navigation:q=${preferencias.getString("ubicacion_propio", "")}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                mapIntent.resolveActivity(context.packageManager)?.let {
                    activity.startActivity(mapIntent)
                }
            })
        }


    }


}