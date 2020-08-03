package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.model.vehiculo.*
import com.juansebastian.pruebajuniorandroid.view.DetalleVehiculoPropioActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class VehiculoDisponiblePresenter(var context: Context, val listView: ListView, val activity: Activity):
        VehiculoDisponibleInterface, Callback<Vehiculo> {

    private val vehiculoInterface: VehiculoInterface = Vehiculo(this)
    private var adapterVehiculoDisponible: AdapterVehiculoDisponible? = null

    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        return vehiculoInterface.getArrayItems(itemsAgregar)
    }

    override fun mostrarVehiculos() {

        val call: Call<Vehiculo> = ApiVehiculoAdapterService.getApiService()!!.getVehiculos()
        call.enqueue(this)

    }

    override fun onFailure(call: Call<Vehiculo>?, t: Throwable?) {
        Toast.makeText(context, "Falla de conexión", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Vehiculo>?, response: Response<Vehiculo>?) {
        if (response!!.isSuccessful) run {
            val vehiculo: Vehiculo = response.body()

            val listaVehiculos: ArrayList<Vehiculo> = vehiculo.getVehiculos() as ArrayList<Vehiculo>

            val items = ArrayList<Vehiculo>()
            for (i in 0.. listaVehiculos.size) {
                if (i != listaVehiculos.size) {
                    val dataVehiculos: Any = listaVehiculos.get(i)
                    val dataVehiculoLinked: LinkedTreeMap<Any, Any> = dataVehiculos as LinkedTreeMap<Any, Any>

                    val marca = dataVehiculoLinked.get("brand").toString()
                    val modelo = dataVehiculoLinked.get("model").toString()
                    val imagen = dataVehiculoLinked.get("image").toString()
                    val favorito = dataVehiculoLinked.get("favorite").toString()
                    val eliminacion = dataVehiculoLinked.get("delet_request").toString()
                    val estado = dataVehiculoLinked.get("state").toString()
                    val ubicacion = dataVehiculoLinked.get("location")
                    val coleccion = dataVehiculoLinked.get("collection_name").toString()
                    val combustion = dataVehiculoLinked.get("combustion_type").toString()

                    val dataUbicacion: LinkedTreeMap<Any, Any> = ubicacion as LinkedTreeMap<Any, Any>
                    val coordenadas: String = "${dataUbicacion.get("latitude").toString()},${dataUbicacion.get("longitude").toString()}"

                    if (favorito.equals("true")) {
                        items.add(Vehiculo(marca, modelo, imagen,
                                context.getDrawable(R.drawable.estrella), eliminacion, estado,
                                coordenadas, coleccion, combustion))
                    } else {
                        items.add(Vehiculo(marca, modelo, imagen,
                                null, eliminacion, estado,
                                coordenadas, coleccion, combustion))
                    }



                } else {
                    break
                }
            }

            adapterVehiculoDisponible = AdapterVehiculoDisponible(context.applicationContext, getArrayItems(items))
            listView.adapter = adapterVehiculoDisponible
            mostrarDetalle(listView, adapterVehiculoDisponible!!)
        }
    }

    override fun mostrarDetalle(listView: ListView, adapterVehiculo: AdapterVehiculoDisponible) {
        val preferencias: SharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        listView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val vehiculo: Vehiculo = adapterVehiculo.getItem(i)

            val editor: SharedPreferences.Editor = preferencias.edit()
            editor.putString("tipo_vehiculo", "disponible")
            editor.putString("marca_disponible", vehiculo.getMarca())
            editor.putString("modelo_disponible", vehiculo.getModelo())
            editor.putString("favorito_disponible", vehiculo.getFavorito().toString())
            editor.putString("estado_disponible", vehiculo.getEstado())
            editor.putString("coleccion_disponible", vehiculo.getColeccion())
            editor.putString("combustion_disponible", vehiculo.getCombustion())
            editor.putString("ubicacion_disponible", vehiculo.getUbicacion())
            editor.putString("eliminacion_disponible", vehiculo.getSolicitud())
            editor.putString("imagen_disponible", vehiculo.getImagen())
            editor.commit()

            val intent: Intent = Intent(activity, DetalleVehiculoPropioActivity::class.java)
            activity.startActivity(intent)

        })

    }

}