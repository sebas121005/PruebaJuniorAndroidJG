package com.juansebastian.pruebajuniorandroid.view.vehiculo

import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface VehiculoPropioFragmentInterface {
    fun mostrarVehiculos(listView: ListView, agregarVehiculo: FloatingActionButton)
}