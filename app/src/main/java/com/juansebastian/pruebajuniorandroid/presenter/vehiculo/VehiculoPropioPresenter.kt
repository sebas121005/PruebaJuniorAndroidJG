package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.FragmentActivity
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.model.vehiculo.AdapterVehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.VehiculoInterface
import com.juansebastian.pruebajuniorandroid.view.DetalleVehiculoPropioActivity
import org.json.JSONObject
import java.util.ArrayList

class VehiculoPropioPresenter(var context: Context, val activity: FragmentActivity): VehiculoPropioInterface {
    private val vehiculoInterface: VehiculoInterface = Vehiculo(this)
    private var adapterVehiculo: AdapterVehiculo? = null


    override fun mostrarVehiculos(listView: ListView) {
        val dbVehiculo = DbVehiculo(context)
        val db = dbVehiculo.readableDatabase
        val projection = arrayOf("rowid", DbVehiculo.FeedEntry.COLUMN_NAME_USUARIO, DbVehiculo.FeedEntry.COLUMN_NAME_DATA)
        val selection = "${DbVehiculo.FeedEntry.COLUMN_NAME_USUARIO} = ?"
        val selectionArgs = arrayOf("123")

        val sortOrder = "${"rowid"} DESC"

        val cursor = db.query(
                DbVehiculo.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )

        var dataJson: JSONObject? = null

        val items = ArrayList<Vehiculo>()

        if (cursor!= null) {
            with(cursor) {
                while (moveToNext()) {
                    dataJson = JSONObject(cursor.getString(2))
                    Log.e("DATA", dataJson.toString())

                    if (dataJson!!.getString("favorito").equals("Si")) {
                        items.add(Vehiculo(dataJson!!.getString("marca"), dataJson!!.getString("modelo"),
                                null, context.getDrawable(R.drawable.estrella)))
                    } else {
                        items.add(Vehiculo(dataJson!!.getString("marca"), dataJson!!.getString("modelo"),
                                null, null))
                    }
                }
            }
        } else {
            Toast.makeText(context, "Sin vehículos disponibles", Toast.LENGTH_SHORT).show()
        }


        adapterVehiculo = AdapterVehiculo(context.applicationContext, getArrayItems(items))
        listView.adapter = adapterVehiculo
        mostrarDetalle(listView, adapterVehiculo!!)
    }

    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        return vehiculoInterface.getArrayItems(itemsAgregar)
    }

   fun mostrarDetalle(listView: ListView, adapterVehiculo: AdapterVehiculo) {
       listView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
           val vehiculo: Vehiculo = adapterVehiculo.getItem(i)

           Log.e("DATA", "Modelo: ${vehiculo.getModelo()}, Marca: ${vehiculo.getMarca()}")
           val intent: Intent = Intent(activity, DetalleVehiculoPropioActivity::class.java)
           activity.startActivity(intent)
       })
   }



}