package com.example.proyprueba2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyprueba2.R

enum class ProviderType {
    // Autenticación básica: email y contraseña
    BASIC,
    GOOGLE
}

@Suppress("UNREACHABLE_CODE")
class MiPerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mi_perfil, container, false)


        /**
        // Setup
        val bundle = Intent.extras
        val email = bundle.getString("email")
        val provider = bundle.getString("provider")
        setup(email ?: "", provider ?: "")


        // Guardar el Proveedor y el usuario autenticado

        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        // Guardar una cadena de texto
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        */
    }

    /**
    private fun setup(email: String, provider: String) {

        // Agregamos los valores a los id de los layouts
        emailTextView.text = email
        providerTextView.text = provider

        // Uso del botón "Cerrar Sesión"
        logOutButton.setOnClickListener {

            // Borrado de datos
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            // Llamar a FBase
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }


        //------------ Botón que llama a la Activity de Comunicados ------------

        comunicadosButton.setOnClickListener {
            startActivity(Intent(this, ComunicadosActivity::class.java))
        }

    }
     */


}