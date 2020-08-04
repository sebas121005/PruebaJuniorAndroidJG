package com.juansebastian.pruebajuniorandroid.presenter.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.juansebastian.pruebajuniorandroid.MainActivity
import com.juansebastian.pruebajuniorandroid.view.perfil.AgregaPerfilActivity

class UsuarioPresenter(val context: Context, val activity: Activity): UsuarioPresenterInterface {

    override fun obtenerUsuario(usuario: TextInputEditText, contrasena: TextInputEditText, iniciarSesion: Button) {
        val preferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

            if (!preferences.getString("inicio_sesion", "").equals("1")) {
                val editor: SharedPreferences.Editor = preferences.edit()



                iniciarSesion.setOnClickListener(View.OnClickListener {
                    if (usuario.text.toString().equals("usuario1") && contrasena.text.toString().equals("1") ||
                            usuario.text.toString().equals("usuario2") && contrasena.text.toString().equals("2")) {

                            editor.putString("usuario", usuario.text.toString())
                            editor.putString("contrasena", contrasena.text.toString())
                            editor.putString("inicio_sesion", "1")
                            editor.commit()
                            val intent: Intent = Intent(activity, AgregaPerfilActivity::class.java)
                            activity.startActivity(intent)

                    } else {
                        Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }

                })
            } else {
                val intent: Intent = Intent(activity, MainActivity::class.java)
                activity.startActivity(intent)

            }


    }
}