package com.example.proyprueba2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.proyprueba2.fragment.ComparaFragment
import com.example.proyprueba2.fragment.InicioFragment
import com.example.proyprueba2.fragment.MiPerfilFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType {
    // Autenticación básica: email y contraseña
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    private val inicioFragment = InicioFragment()
    private val comparaFragment = ComparaFragment()
    private val miPerfilFragment = MiPerfilFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")


        // Guardar el Proveedor y el usuario autenticado

        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        // Guardar una cadena de texto
            prefs.putString("email", email)
            prefs.putString("provider", provider)
            prefs.apply()

        //--------------- FRAGMENTS ---------------
        replaceFragment(inicioFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_inicio -> replaceFragment(inicioFragment)
                R.id.ic_compara -> replaceFragment(comparaFragment)
                R.id.ic_miperfil -> replaceFragment(miPerfilFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
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

        /*
        ------------ Botón que llama a la Activity de Comunicados ------------

        comunicadosButton.setOnClickListener {
            startActivity(Intent(this, ComunicadosActivity::class.java))
        }

         */



    }
}