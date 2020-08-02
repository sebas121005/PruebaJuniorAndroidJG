package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juansebastian.pruebajuniorandroid.model.vehiculo.AdapterVehiculo
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import java.util.ArrayList

interface VehiculoPropioInterface {
    fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>?

    fun mostrarVehiculos(listView: ListView, agregarVehiculo: FloatingActionButton)

    fun mostrarDetalle(listView: ListView, adapterVehiculo: AdapterVehiculo)
}