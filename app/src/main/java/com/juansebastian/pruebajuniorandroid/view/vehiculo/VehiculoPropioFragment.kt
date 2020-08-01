package com.juansebastian.pruebajuniorandroid.view.vehiculo

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.app.SharedElementCallback
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoPropioPresenter
import com.juansebastian.pruebajuniorandroid.view.AgregarVehiculoActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VehiculoPropioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehiculoPropioFragment : Fragment(), VehiculoPropioFragmentInterface {

    private var vehiculoPropioPresenter: VehiculoPropioPresenter? = null

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(com.juansebastian.pruebajuniorandroid.view.vehiculo.ARG_PARAM1)
            param2 = it.getString(com.juansebastian.pruebajuniorandroid.view.vehiculo.ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_vehiculo_propio, container, false)
        val listaVehiculos = view.findViewById(R.id.lista_vehiculos_propios) as ListView
        val agregarVehiculo: FloatingActionButton = view.findViewById(R.id.agregar_vehiculo)
        vehiculoPropioPresenter = VehiculoPropioPresenter(requireContext(), requireActivity())


        agregarVehiculo.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(activity, AgregarVehiculoActivity::class.java)
            activity?.startActivity(intent)})

        mostrarVehiculos(listaVehiculos, agregarVehiculo)


        return view

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VehiculoPropioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                VehiculoPropioFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun mostrarVehiculos(listView: ListView, agregarVehiculo: FloatingActionButton) {
        vehiculoPropioPresenter!!.mostrarVehiculos(listView, agregarVehiculo)
    }


}