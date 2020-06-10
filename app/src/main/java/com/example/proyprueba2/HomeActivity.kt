package com.example.proyprueba2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    // Autenticación básica: email y contraseña
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup
        val bundle = intent.extras
        val email=bundle?.getString("email")
        val provider =bundle?.getString("provider")
        setup(email?:"",provider?:"")

        // Guardar el Proveedor y el usuario autenticado
        val prefs = getSharedPreferences(getString(R.Strings.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()

    }

    private fun setup(email:String, provider:String){
        //
        title = "Inicio"
        // Agregamos los valores a los id de los layouts
        emailTextView.text = email
        providerTextView.text=provider

        // Uso del botón "Cerrar Sesión"
        logOutButton.setOnClickListener {
            // Llamar a FBase
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}