package com.example.proyprueba2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyprueba2.fragment.ProviderType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash Screen
        Thread.sleep(2000)
        setTheme(R.style.AppTheme) // Usar el tema
        // Por Defecto
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Obtain the FirebaseAnalytics instance. Además de mandar eventos a G.Analytics
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        // Setup
        setup()
        session()
    }

    override fun onStart() {
        super.onStart()

        authLayout.visibility = View.VISIBLE
    }


    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)

        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            authLayout.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }
    }


    private fun setup() {

        title = "¡Bienvenid@!"

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
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }


        // Lógica al botón "Acceder"

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                // Usando el servicio de FBase ... y agregar el Listener para que se notifique que ha ingresado emsil&pass correctamente
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener {

                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        // Lógica al botón "Google"
        googleButton.setOnClickListener {
            // Configuración Login con Google
            val googleConf =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
            // Cliente de autenticación Google
            val googleClient = GoogleSignIn.getClient(this, googleConf)
                googleClient.signOut()
                startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

    }


    private fun showAlert() {
        // Validando los mensajes de alerta | ingreso incorrecto de datos.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se ha podido autenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        // Registra
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun showHome(email: String, provider: ProviderType) {
        // Mostrar y ajustar la nueva pantalla
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
                putExtra("email", email)
                putExtra("provider", provider.name)
        }
        //Navegar a la nueva pantalla
        startActivity(homeIntent)
    }

    // Reimplementando el 'activity', para que  responda con el LOGIN CON GOOGLE
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==GOOGLE_SIGN_IN){
            // Recuperando respuesta
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {

                val account = task.getResult(ApiException::class.java)

                if (account!=null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    // Pasando la credencial a Firebase
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                              showHome(account.email?:"", ProviderType.GOOGLE)
                            } else {
                                showAlert()
                            }
                        }
                }


            }catch (e:ApiException){
                showAlert()
            }

        }
    }


}