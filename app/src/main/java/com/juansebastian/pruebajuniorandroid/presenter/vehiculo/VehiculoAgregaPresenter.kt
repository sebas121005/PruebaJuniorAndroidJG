package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
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
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.VehiculoInterface
import org.json.JSONObject
import java.util.ArrayList

class VehiculoAgregaPresenter(var context: Context, val activity: Activity): VehiculoAgregarInterface,
        VehiculoPropioInterface {
    private var imagenBitmap: Bitmap? = null

    override fun guardarImagen(requestCode: Int, resultCode: Int, data: Intent?, REQUEST_IMAGE_CAPTURE: Int,
                               imagenVehiculo: ImageView?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            imagenBitmap = data?.extras?.get("data") as Bitmap
            imagenVehiculo?.setImageBitmap(imagenBitmap)

        }
    }

    override fun guardarVehiculo(editMarca: TextInputEditText, editModelo: TextInputEditText, spinnerEstado: Spinner,
                        spinnerFavorito: Spinner) {
        val marca = editMarca.text
        val modelo = editModelo.text
        val estado = spinnerEstado.selectedItem
        val favorito = spinnerFavorito.selectedItem

        val infoJson = JSONObject()
        infoJson.put("marca", marca)
        infoJson.put("modelo", modelo)
        infoJson.put("estado", estado)
        infoJson.put("favorito", favorito)
        infoJson.put("imagen", imagenBitmap)


        val dbVehiculo = DbVehiculo(context)
        val db = dbVehiculo.writableDatabase


       val values = ContentValues().apply {
            put(DbVehiculo.FeedEntry.COLUMN_NAME_USUARIO, "123")
            put(DbVehiculo.FeedEntry.COLUMN_NAME_DATA, "$infoJson")
        }

        val nuevaFila = db?.insert("VEHICULO", null, values)

    }

    override fun llenarSpinner(spinnerEstado: Spinner, spinnerFavorito: Spinner) {
        val datosEstado = arrayOf("Comprado", "Desactivado", "Propio", "Disponible")
        val arrayAdapterEstado: ArrayAdapter<String> = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
                datosEstado)

        spinnerEstado.adapter = arrayAdapterEstado

        val datosFavorito = arrayListOf("Si", "No")
        val arrayAdapterFavorito: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_list_item_1,
                datosFavorito)

        spinnerFavorito.adapter = arrayAdapterFavorito

    }

    override fun tomarImagen(REQUEST_IMAGE_CAPTURE: Int) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                ActivityCompat.startActivityForResult(activity, takePictureIntent, REQUEST_IMAGE_CAPTURE, null)
            }
        }
    }




    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        TODO("Not yet implemented")
    }

    override fun mostrarVehiculos(listView: ListView) {
        TODO("Not yet implemented")
    }
}