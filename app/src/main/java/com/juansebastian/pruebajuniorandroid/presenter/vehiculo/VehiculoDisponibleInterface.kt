package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.widget.ListView
import com.juansebastian.pruebajuniorandroid.model.vehiculo.AdapterVehiculoDisponible
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import java.util.ArrayList

interface VehiculoDisponibleInterface {
    fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>?

    fun mostrarVehiculos()

    fun mostrarDetalle(listView: ListView, adapterVehiculo: AdapterVehiculoDisponible)
}