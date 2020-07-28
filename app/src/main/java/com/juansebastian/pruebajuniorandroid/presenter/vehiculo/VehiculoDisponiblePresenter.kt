package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.content.Context
import android.widget.ListView
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.model.vehiculo.AdapterVehiculoDisponible
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.VehiculoInterface
import java.util.ArrayList

class VehiculoDisponiblePresenter(var context: Context): VehiculoDisponibleInterface {

    private val vehiculoInterface: VehiculoInterface = Vehiculo(this)
    private var adapterVehiculoDisponible: AdapterVehiculoDisponible? = null

    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        return vehiculoInterface.getArrayItems(itemsAgregar)
    }

    override fun mostrarVehiculos(listView: ListView) {
        val items = ArrayList<Vehiculo>()
        items.add(Vehiculo("Chevrolet", "Nuevo", vehiculoInterface.getImagen(),
                context.getDrawable(R.drawable.estrella)))
        items.add(Vehiculo("Chevrolet", "Nuevo", vehiculoInterface.getImagen(),
                context.getDrawable(R.drawable.estrella)))
        items.add(Vehiculo("Chevrolet", "Nuevo", vehiculoInterface.getImagen(),
                context.getDrawable(R.drawable.estrella)))

        adapterVehiculoDisponible = AdapterVehiculoDisponible(context.applicationContext, getArrayItems(items))
        listView.adapter = adapterVehiculoDisponible
    }

}