package com.juansebastian.pruebajuniorandroid.view.perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.juansebastian.pruebajuniorandroid.R
import com.juansebastian.pruebajuniorandroid.presenter.perfil.PerfilPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilFragment : Fragment(), PerfilFragmentInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var perfilPresenter: PerfilPresenter? = null

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
        val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)
        val cc: TextView = view.findViewById(R.id.cc_perfil)
        val nombre: TextView = view.findViewById(R.id.nombre_perfil)
        val contrasena: TextView = view.findViewById(R.id.contrasena_perfil)
        val imagen: ImageView = view.findViewById(R.id.perfil_imagen)
        val ubicacion: TextView = view.findViewById(R.id.ubicacion_perfil)
        val tomarUbicacionPerfil: ImageButton = view.findViewById(R.id.tomar_ubicacion_perfil)
        perfilPresenter = PerfilPresenter(context!!, requireActivity())

        mostrarDetalle(cc, nombre, contrasena, imagen, ubicacion, tomarUbicacionPerfil)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PerfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PerfilFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun mostrarDetalle(cc: TextView, nombre: TextView, contrasena: TextView, imagenPerfil: ImageView,
                                ubicacion: TextView, tomarUbicacionPerfil: ImageButton) {
        perfilPresenter!!.mostrarDetalle(cc, nombre, contrasena, imagenPerfil, ubicacion, tomarUbicacionPerfil)
    }
}