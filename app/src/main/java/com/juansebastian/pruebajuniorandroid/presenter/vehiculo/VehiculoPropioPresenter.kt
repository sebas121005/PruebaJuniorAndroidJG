package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.FragmentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.internal.LinkedTreeMap
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.model.vehiculo.AdapterVehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.ApiVehiculoAdapterService
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.VehiculoInterface
import com.juansebastian.pruebajuniorandroid.view.DetalleVehiculoPropioActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class VehiculoPropioPresenter(val context: Context, val activity: FragmentActivity): VehiculoPropioInterface,
        Callback<Vehiculo> {

    private val vehiculoInterface: VehiculoInterface = Vehiculo(this)
    private var adapterVehiculo: AdapterVehiculo? = null
    val preferencias: SharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)


    override fun mostrarVehiculos(listView: ListView, agregarVehiculo: FloatingActionButton) {
        val dbVehiculo = DbVehiculo(context)
        val db = dbVehiculo.readableDatabase
        val projection = arrayOf("rowid", DbVehiculo.FeedEntry.COLUMN_NAME_USUARIO, DbVehiculo.FeedEntry.COLUMN_NAME_DATA)
        val selection = "${DbVehiculo.FeedEntry.COLUMN_NAME_USUARIO} = ?"
        val selectionArgs = arrayOf(preferencias.getString("usuario", ""))

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
            consultarImagen()
            with(cursor) {
                while (moveToNext()) {
                    dataJson = JSONObject(cursor.getString(2))

                        if (dataJson!!.getString("favorito").equals("Si")) {
                            items.add(Vehiculo(dataJson!!.getString("marca"), dataJson!!.getString("modelo"),
                                    preferencias.getString("imagen_propio", ""), context.getDrawable(R.drawable.estrella),
                                    dataJson!!.getString("eliminacion"), dataJson!!.getString("estado"),
                                    dataJson!!.getString("ubicacion"), dataJson!!.getString("coleccion"),
                                    dataJson!!.getString("combustion")))
                        } else {
                            items.add(Vehiculo(dataJson!!.getString("marca"), dataJson!!.getString("modelo"),
                                    preferencias.getString("imagen_propio", ""), null,
                                    dataJson!!.getString("eliminacion"), dataJson!!.getString("estado"),
                                    dataJson!!.getString("ubicacion"), dataJson!!.getString("coleccion"),
                                    dataJson!!.getString("combustion")))
                        }

                    if (cursor.getString(0).equals("3")) {
                        agregarVehiculo.hide()
                        Toast.makeText(context, "Ha llegado al límite de sus vehículos", Toast.LENGTH_SHORT).show()
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

   override fun mostrarDetalle(listView: ListView, adapterVehiculo: AdapterVehiculo) {
       val preferencias: SharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
       listView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
           val vehiculo: Vehiculo = adapterVehiculo.getItem(i)

           val editor: SharedPreferences.Editor = preferencias.edit()
           editor.putString("tipo_vehiculo", "propio")
           editor.putString("marca_propio", vehiculo.getMarca())
           editor.putString("modelo_propio", vehiculo.getModelo())
           editor.putString("favorito_propio", vehiculo.getFavorito().toString())
           editor.putString("estado_propio", vehiculo.getEstado())
           editor.putString("coleccion_propio", vehiculo.getColeccion())
           editor.putString("combustion_propio", vehiculo.getCombustion())
           editor.putString("ubicacion_propio", vehiculo.getUbicacion())
           editor.putString("eliminacion_propio", vehiculo.getSolicitud())
           editor.commit()

           val intent: Intent = Intent(activity, DetalleVehiculoPropioActivity::class.java)
           activity.startActivity(intent)

       })
   }

    override fun consultarImagen() {
        val call: Call<Vehiculo> = ApiVehiculoAdapterService.getApiService()!!.getVehiculos()
        call.enqueue(this)
    }

    override fun onFailure(call: Call<Vehiculo>?, t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<Vehiculo>?, response: Response<Vehiculo>?) {
        if (response!!.isSuccessful) run {
            val vehiculo: Vehiculo = response.body()

            val listaVehiculos: ArrayList<Vehiculo> = vehiculo.getVehiculos() as ArrayList<Vehiculo>

            val items = ArrayList<Vehiculo>()
            for (i in 0.. listaVehiculos.size) {
                if (i == listaVehiculos.size) {
                    break
                }
                val dataVehiculos: Any = listaVehiculos.get(i)
                val dataVehiculoLinked: LinkedTreeMap<Any, Any> = dataVehiculos as LinkedTreeMap<Any, Any>
                val imagen = dataVehiculoLinked.get("image").toString()


                val editor: SharedPreferences.Editor = preferencias.edit()
                editor.putString("imagen_propio", imagen)
                editor.commit()

            }
        }
    }


}