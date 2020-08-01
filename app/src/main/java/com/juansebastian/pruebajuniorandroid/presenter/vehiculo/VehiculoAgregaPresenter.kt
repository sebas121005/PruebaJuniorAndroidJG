package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.textfield.TextInputEditText
import com.juansebastian.pruebajuniorandroid.MainActivity
import com.juansebastian.pruebajuniorandroid.view.vehiculo.VehiculoPropioFragment
import org.json.JSONObject

class VehiculoAgregaPresenter(var context: Context, val activity: Activity): VehiculoAgregarInterface {


    override fun guardarImagen(requestCode: Int, resultCode: Int, data: Intent?, REQUEST_IMAGE_CAPTURE: Int,
                               imagenVehiculo: ImageView?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imagenBitmap = data?.extras?.get("data") as Bitmap
            imagenVehiculo?.setImageBitmap(imagenBitmap)

        }
    }

    override fun guardarVehiculo(editMarca: TextInputEditText, editModelo: TextInputEditText, spinnerEstado: Spinner,
                        spinnerFavorito: Spinner, editColeccion: TextInputEditText, spinnerCombustion: Spinner,
                                 spinnerEliminacion: Spinner, preferencias: SharedPreferences) {
        val marca = editMarca.text
        val modelo = editModelo.text
        val estado = spinnerEstado.selectedItem
        val favorito = spinnerFavorito.selectedItem
        val coleccion = editColeccion.text
        val combustion = spinnerCombustion.selectedItem
        val eliminacion = spinnerEliminacion.selectedItem
        val ubicacion = preferencias.getString("ubicacion", "")

        val infoJson = JSONObject()
        infoJson.put("marca", marca)
        infoJson.put("modelo", modelo)
        infoJson.put("estado", estado)
        infoJson.put("favorito", favorito)
        infoJson.put("coleccion", coleccion)
        infoJson.put("combustion", combustion)
        infoJson.put("eliminacion", eliminacion)
        infoJson.put("ubicacion", ubicacion)


        val dbVehiculo = DbVehiculo(context)
        val db = dbVehiculo.writableDatabase


       val values = ContentValues().apply {
            put(DbVehiculo.FeedEntry.COLUMN_NAME_USUARIO, "123")
            put(DbVehiculo.FeedEntry.COLUMN_NAME_DATA, "$infoJson")
        }

        val nuevaFila = db?.insert("VEHICULO", null, values)

        val intent: Intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)

    }

    override fun llenarSpinner(spinnerEstado: Spinner, spinnerFavorito: Spinner, spinnerCombustion: Spinner,
                        spinnerEliminacion: Spinner) {
        val datosEstado = arrayOf("Comprado", "Desactivado", "Propio", "Disponible")
        val arrayAdapterEstado: ArrayAdapter<String> = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
                datosEstado)

        spinnerEstado.adapter = arrayAdapterEstado

        val datosFavorito = arrayListOf("Si", "No")
        val arrayAdapterFavorito: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_list_item_1,
                datosFavorito)

        spinnerFavorito.adapter = arrayAdapterFavorito

        val datosCombustion = arrayListOf("Hidrógeno", "Eléctrico", "Eléctrico Hibrido",
                                            "Combustible flexible", "Gas natural", "Gas licuado")
        val arrayAdapterCombustion: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_list_item_1,
                                                            datosCombustion)
        spinnerCombustion.adapter = arrayAdapterCombustion

        val datosEliminacion = arrayListOf("Si", "No")
        val arrayAdapterEliminacion: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_list_item_1,
                datosEliminacion)

        spinnerEliminacion.adapter = arrayAdapterEliminacion

    }

    override fun tomarImagen(REQUEST_IMAGE_CAPTURE: Int) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                ActivityCompat.startActivityForResult(activity, takePictureIntent, REQUEST_IMAGE_CAPTURE, null)
            }
        }
    }

}