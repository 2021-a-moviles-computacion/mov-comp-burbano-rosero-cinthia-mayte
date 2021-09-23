package com.example.examen2b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_registrar_doctor.*
import java.util.*


class RegistrarDoctor : AppCompatActivity() {

    lateinit var  firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_doctor)
        val botonCancelarRDoctor=findViewById<Button>(R.id.btn_cancelar_doctor)

        inicializarFirebase()

        botonCancelarRDoctor.setOnClickListener {
            val intent= Intent(this, MostrarDoctor::class.java)
            startActivity(intent)
        }
        val botonGuardar= findViewById<Button>(R.id.btn_registrar_doctor)
        botonGuardar.setOnClickListener {
            val nombre= editText_nombre_doctor.text.toString()
            val cedula= editText_cedula_doctor.text.toString()
            val editEdad = editText_edad_doctor.text.toString().toInt()
            val telefono= editText_telefono_doctor.text.toString()
            val correo= editText_correo_doctor.text.toString()
            val especialidadD=editText_especialidad.text.toString()
            if(cedula.isEmpty() or nombre.isEmpty() or editEdad.equals(null) or telefono.isEmpty() or correo.isEmpty() or especialidadD.isEmpty() ){
                validarCampos()
            }else{
                crearDoctor()
                Toast.makeText(this, "Doctor registrado correctamente",Toast.LENGTH_LONG).show()

            }
        }



    }
    fun validarCampos(){
        val nombre= editText_nombre_doctor.text.toString()
        val cedula= editText_cedula_doctor.text.toString()
       val edad= editText_edad_doctor.text.toString().toInt()
        val telefono= editText_telefono_doctor.text.toString()
        val correo= editText_correo_doctor.text.toString()
        val especialidadD=editText_especialidad.text.toString()
        if(cedula.isEmpty()){
            editText_cedula_doctor.setError("Required")
        }
        if(nombre.isEmpty()){
            editText_nombre_doctor.setError("Required")
        }
        if(telefono.isEmpty()){
            editText_telefono_doctor.setError("Required")
        }
        if(correo.isEmpty()){
            editText_correo_doctor.setError("Required")
        }
        if(edad.equals(null)){
            editText_edad_doctor.setError("Required")
        }
        if(especialidadD.isEmpty()){
            editText_especialidad.setError("Required")
        }
    }
    fun limpiar(){
        editText_cedula_doctor.setText("")
        editText_nombre_doctor.setText("")
        editText_edad_doctor.setText("")
        editText_correo_doctor.setText("")
        editText_telefono_doctor.setText("")
        editText_especialidad.setText("")
    }
    fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference()
    }
    fun crearDoctor(){
        val nuevoDoctor= hashMapOf<String,Any>(
            "Cedula" to editText_cedula_doctor.text.toString(),
            "Nombre" to editText_nombre_doctor.text.toString(),
            "Edad" to editText_edad_doctor.text.toString().toInt(),
            "Telefono" to editText_telefono_doctor.text.toString(),
            "Correo" to editText_correo_doctor.text.toString(),
            "Especialidad" to editText_especialidad.text.toString()
        )
        val db = Firebase.firestore
        val referencia = db.collection("Doctor")
        referencia.add(nuevoDoctor)
            .addOnSuccessListener {
                limpiar()
            }
            .addOnFailureListener {  }

    }
}