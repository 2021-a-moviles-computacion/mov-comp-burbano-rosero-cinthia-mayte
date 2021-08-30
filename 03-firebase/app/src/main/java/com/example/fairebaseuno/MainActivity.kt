package com.example.fairebaseuno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val CODIGO_INICIO_SESION=102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        val botonLogin = findViewById<Button>(R.id.buttonbtn_login)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

    }
    fun  llamarLoginUsuario(){
    val providers = arrayListOf(
        //lista de proveedores
        AuthUI.IdpConfig.EmailBuilder().build()
    )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html","https://example.com/privacy.html"
                ).build(), CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_INICIO_SESION->{
                if(resultCode== Activity.RESULT_OK){
                    val usuario= IdpResponse.fromResultIntent(data)
                    //if(usuario!=null){
                    if(usuario?.isNewUser==true){
                        Log.i("firebase-login","Nuevo Usuario")
                        registrarUsuarioPorPrimeraVez(usuario)
                    }else{
                        setearUsuarioFirebase()
                        Log.i("firebase-login","Usuario Antiguo")
                    }
                }else{
                    Log.i("firebase-login","El usuario Cancelo")
                }
            }
            }
        }

        fun setearUsuarioFirebase(){
            val instanciaAuth = FirebaseAuth.getInstance()
            val usuarioLocal = instanciaAuth.currentUser
            if(usuarioLocal !=null){
                if(usuarioLocal.email !=null){
                    val db= Firebase.firestore
                    val referencia = db.collection("usuario")
                        .document(usuarioLocal.email.toString())
                    referencia
                        .get().addOnSuccessListener {
                            val usuarioCargado: FirestoreUsuarioDto? = it.toObject(FirestoreUsuarioDto::class.java)
                            if(usuarioCargado!=null){
                                BAuthUsuario.usuario= BUsuarioFirebase(
                                    usuarioCargado.uid,
                                    usuarioCargado.email,
                                    usuarioCargado.roles
                                )
                            }

                            Log.i("firebase-firestore", "Usuario cargado")
                        }
                        .addOnFailureListener { Log.i("firebase-firestore", "fallo cargar usuario") }
                }

            }
        }

        fun setearBienvenida(){
            val textViewBienvenida =findViewById<TextView>(R.id.tv_bienvenida)
            if(BAuthUsuario.usuario != null){
                textViewBienvenida.text ="Bienvenido ${BAuthUsuario.usuario?.email}"
            }else{
                textViewBienvenida.text= "Ingresa al aplicativo"
            }
        }
        fun registrarUsuarioPorPrimeraVez(usuario:IdpResponse){
                 val identificadorUsuario = usuario.email
                val usuaruioLogeado= FirebaseAuth.getInstance()
                    .getCurrentUser()
                if(usuario.email !=null && usuaruioLogeado != null){
                    //roles
                    val db = Firebase.firestore
                    val rolesUsuario= arrayListOf("usuario") //creamos el usuario de permisos
                    val nuevoUsuario = hashMapOf<String, Any>(
                        "roles" to rolesUsuario,
                        "uid" to usuaruioLogeado.uid,
                        "email" to identificadorUsuario.toString()
                    )

                    db.collection("usuario").document(identificadorUsuario.toString())//
                        .set(nuevoUsuario)
                        .addOnSuccessListener {
                            Log.i("firebase-firestore", "Se creo")
                            setearUsuarioFirebase()
                        }
                        .addOnFailureListener {
                            Log.i("firebase-firestore", "Fallo")
                        }


                }else {
                    Log.i("firebase-login","ERROR")
                }
            }
        }
