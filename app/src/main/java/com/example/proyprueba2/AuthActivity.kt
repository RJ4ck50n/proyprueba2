package com.example.proyprueba2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

private lateinit var auth: AuthActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Splash Screen
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

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
                            showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }


        // Lógica al botón "Acceder"

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                // Usando el servicio de FBase .... y agregar el Listener para que se notifique que ha ingresado emsil&pass correctamente.
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


    private fun showHome(email: String, provider: ProviderType) {
        // Mostrar y ajustar la nueva pantalla
        val homeIntent = Intent(this, HomeActivity::class.java)
            .apply {
                // Le pasamos(como parámetro) el email y el proveedor
                putExtra("email", email)
                putExtra("provider", provider.name)
            }

        //Navegar a la nueva pantalla
        startActivity(homeIntent)
    }



}