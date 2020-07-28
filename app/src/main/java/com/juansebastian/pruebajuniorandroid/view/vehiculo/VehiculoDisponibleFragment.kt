package com.juansebastian.pruebajuniorandroid.view.vehiculo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoDisponiblePresenter
import com.juansebastian.pruebajuniorandroid.presenter.vehiculo.VehiculoPropioPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VehiculoDisponibleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehiculoDisponibleFragment : Fragment(), VehiculoDisponibleFragmentInterface {

    private var vehiculoDisponiblePresenter: VehiculoDisponiblePresenter? = null

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_vehiculo_disponible, container, false)
        val listaVehiculos = view.findViewById(R.id.lista_vehiculos_disponibles) as ListView
        vehiculoDisponiblePresenter = VehiculoDisponiblePresenter(requireContext())

        mostrarVehiculos(listaVehiculos)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VehiculoDisponibleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                VehiculoDisponibleFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun mostrarVehiculos(listView: ListView) {
        vehiculoDisponiblePresenter!!.mostrarVehiculos(listView)
    }
}