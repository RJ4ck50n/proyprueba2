package com.example.proyprueba2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash Screen
        Thread.sleep(4000)
        setTheme(R.style.AppTheme) // Usar el tema
        // Por Defecto
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Setup
        setup()

        session()
    }

    override fun onStart(){
        super.onStart()

        authLayout.visibility = View.VISIBLE
    }


    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email",null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider !=null){
            authLayout.visibility = View.INVISIBLE
           showHome(email, ProviderType.valueOf(provider))
        }
    }


    private fun setup() {

        title = "Autenticación"

        // Lógica al botón "Registro"

        signUpButton.setOnClickListener {
            // Validar que se ha ingresado email y contraseña (no están vacíos)
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                // Usando el servicio de FBase .... y agregar el Listener para que se notifique que ha ingresado emsil&pass ha sido correcta.
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }


        // Lógica al botón "Acceder"

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                // Usando el servicio de FBase .... y agregar el Listener para que se notifique que ha ingresado emsil&pass correctamente
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

    }


    private fun showAlert() {
        // Validando los mensajes de alerta | ingreso incorrecto de datos.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        // Registra
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun showHome(email:String, provider:ProviderType) {
        // Mostrar y ajustar la nueva pantalla
        val homeIntent = Intent(this, HomeActivity::class.java)
            .apply {
                // Le pasamos(como parámetro) el email y el proveedor
                putExtra("email", email)
                putExtra("proveedor", provider.name)
            }

        //Navegar a la nueva pantalla
        startActivity(homeIntent)

    }



}