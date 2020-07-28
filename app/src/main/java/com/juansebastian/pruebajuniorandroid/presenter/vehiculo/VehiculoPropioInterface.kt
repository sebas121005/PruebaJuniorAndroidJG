package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.widget.ListView
import com.juansebastian.pruebajuniorandroid.model.vehiculo.Vehiculo
import java.util.ArrayList

interface VehiculoPropioInterface {
    fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>?

    fun mostrarVehiculos(listView: ListView)
}