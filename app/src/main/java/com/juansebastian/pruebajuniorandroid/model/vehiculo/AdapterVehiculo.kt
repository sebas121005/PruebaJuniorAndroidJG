package com.juansebastian.pruebajuniorandroid.model.vehiculo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoPropioInterface
import com.squareup.picasso.Picasso
import java.util.ArrayList

class AdapterVehiculo(private val context: Context, private val items: ArrayList<Vehiculo>?): BaseAdapter(),
        VehiculoPropioInterface {

    private var vehiculoInterface: VehiculoInterface = Vehiculo(this)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        vehiculoInterface = getItem(position)
        var convertView: View = LayoutInflater.from(context).inflate(R.layout.lista_vehiculos, null)

        val marca = convertView.findViewById<View>(R.id.marca_vehiculo) as TextView
        val modelo = convertView.findViewById<View>(R.id.modelo_vehiculo) as TextView
        val imagen = convertView.findViewById<View>(R.id.lista_imagen_vehiculo) as ImageView
        val favorito = convertView.findViewById<View>(R.id.imagen_favorito) as ImageView

        marca.text = vehiculoInterface.getMarca()
        modelo.text = vehiculoInterface.getModelo()
        favorito.setImageDrawable(vehiculoInterface.getFavorito())
        Picasso.get().load(vehiculoInterface.getImagen()).into(imagen)

        return convertView

    }

    override fun getItem(position: Int): Vehiculo {
        return items!![position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
       return items!!.size
    }

    override fun getArrayItems(itemsAgregar: ArrayList<Vehiculo>?): ArrayList<Vehiculo>? {
        TODO("Not yet implemented")
    }

    override fun mostrarVehiculos(listView: ListView, agregarVehiculo: FloatingActionButton) {
        TODO("Not yet implemented")
    }

    override fun mostrarDetalle(listView: ListView, adapterVehiculo: AdapterVehiculo) {
        TODO("Not yet implemented")
    }
}