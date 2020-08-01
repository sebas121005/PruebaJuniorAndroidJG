package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.content.Context
import android.util.Log
import android.widget.ListView
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.model.vehiculo.AdapterVehiculoDisponible
import com.juansebastian.pruebajuniorandroid.model.vehiculo.ApiVehiculoAdapterService
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.VehiculoInterface
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class VehiculoDisponiblePresenter(var context: Context): VehiculoDisponibleInterface, Callback<Vehiculo> {

    private val vehiculoInterface: VehiculoInterface = Vehiculo(this)
    private var adapterVehiculoDisponible: AdapterVehiculoDisponible? = null

    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        return vehiculoInterface.getArrayItems(itemsAgregar)
    }

    override fun mostrarVehiculos(listView: ListView) {

        val call: Call<Vehiculo> = ApiVehiculoAdapterService.getApiService()!!.getVehiculos()
        call.enqueue(this)

        val items = ArrayList<Vehiculo>()
        items.add(Vehiculo("Chevrolet", "Nuevo", vehiculoInterface.getImagen(),
                context.getDrawable(R.drawable.estrella), "", "", "", "", ""))
        items.add(Vehiculo("Chevrolet", "Nuevo", vehiculoInterface.getImagen(),
                context.getDrawable(R.drawable.estrella), "", "", "", "", ""))
        items.add(Vehiculo("Chevrolet", "Nuevo", vehiculoInterface.getImagen(),
                context.getDrawable(R.drawable.estrella), "", "", "", "", ""))

        adapterVehiculoDisponible = AdapterVehiculoDisponible(context.applicationContext, getArrayItems(items))
        listView.adapter = adapterVehiculoDisponible
    }

    override fun onFailure(call: Call<Vehiculo>?, t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<Vehiculo>?, response: Response<Vehiculo>?) {
        if (response!!.isSuccessful) run {
            val vehiculo: Vehiculo = response.body()

            val arrayJson: JSONArray = JSONArray(response)
            
        }
    }

}