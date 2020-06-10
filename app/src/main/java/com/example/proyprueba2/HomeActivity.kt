package com.example.proyprueba2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    // Autenticación básica: email y contraseña
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Se ingresa comentario de prueba
        // Setup
        val bundle = intent.extras
        val email=bundle?.getString("email")
        val provider =bundle?.getString("provider")
        setup(email?:"",provider?:"")
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