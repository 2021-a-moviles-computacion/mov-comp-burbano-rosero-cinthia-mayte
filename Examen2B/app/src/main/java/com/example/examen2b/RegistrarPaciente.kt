package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registrar_doctor.*
import kotlinx.android.synthetic.main.activity_registrar_paciente.*

class RegistrarPaciente : AppCompatActivity() {
    lateinit var  firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_paciente)
        inicializarFirebase()

        val botonCancelarRPaciente=findViewById<Button>(R.id.btn_cancelar_paciente)
        botonCancelarRPaciente.setOnClickListener {
            val intent: Intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val botonCrearrRPaciente=findViewById<Button>(R.id.btn_guardar_paciente)
        botonCrearrRPaciente.setOnClickListener {
            val idDoctor = edit_id_doctor.text.toString()
            val nombre= edit_nombrePaciente.text.toString()
            val cedula= edit_cedulaPaciente.text.toString()
            val edad= edit_edadPaciente.text.toString().toInt()
            val telefono= edit_telf_paciente.text.toString()
            val correo= edit_correoPaciente.text.toString()
            val latitud = edt_latitud.text.toString().toFloat()
            val longitud= edt_longitud.text.toString().toFloat()
            if(idDoctor.isEmpty() or nombre.isEmpty() or cedula.isEmpty() or edad.equals(null) or telefono.isEmpty() or correo.isEmpty() or latitud.equals(null) or longitud.equals(null) ){
                validarCampos()
            }else{
                crearPaciente()
                Toast.makeText(this, "Paciente registrado correctamente", Toast.LENGTH_LONG).show()

            }
        }
    }
    fun validarCampos(){
        val idDoctor = edit_id_doctor.text.toString()
        val nombre= edit_nombrePaciente.text.toString()
        val cedula= edit_cedulaPaciente.text.toString()
        val edad= edit_edadPaciente.text.toString().toInt()
        val telefono= edit_telf_paciente.text.toString()
        val correo= edit_correoPaciente.text.toString()
        val latitud = edt_latitud.text.toString().toFloat()
        val longitud= edt_longitud.text.toString().toFloat()
        if(cedula.isEmpty()){
            edit_cedulaPaciente.setError("Required")
        }
        if(nombre.isEmpty()){
            edit_nombrePaciente.setError("Required")
        }
        if(telefono.isEmpty()){
            edit_telf_paciente.setError("Required")
        }
        if(correo.isEmpty()){
            edit_correoPaciente.setError("Required")
        }
        if(edad.equals(null)){
            edit_edadPaciente.setError("Required")
          }
        if(idDoctor.isEmpty()){
            edit_id_doctor.setError("Required")
        }
        if(latitud.equals(null)){
            edt_latitud.setError("Required")
        }
        if(longitud.equals(null)){
            edt_longitud.setError("Required")
        }
    }
    fun limpiarCampos(){
        edit_cedulaPaciente.setText("")
        edit_nombrePaciente.setText("")
        edit_id_doctor.setText("")
        edit_edadPaciente.setText("")
        edit_telf_paciente.setText("")
        edit_correoPaciente.setText("")
        edt_longitud.setText("")
        edt_latitud.setText("")
    }
    fun crearPaciente(){
        val nuevoPaciente= hashMapOf<String,Any>(
            "IdDoctor" to edit_id_doctor.text.toString(),
            "Cedula" to edit_cedulaPaciente.text.toString(),
            "Nombre" to edit_nombrePaciente.text.toString(),
            "Edad" to edit_edadPaciente.text.toString().toInt(),
            "Telefono" to edit_telf_paciente.text.toString(),
            "Correo" to edit_correoPaciente.text.toString(),
            "Longitud" to edt_longitud.text.toString().toFloat(),
            "Latitud" to edt_latitud.text.toString().toFloat()
        )
        val db = Firebase.firestore
        val referencia = db.collection("Paciente")
        referencia.add(nuevoPaciente)
            .addOnSuccessListener {
                limpiarCampos()
            }
            .addOnFailureListener {  }

    }
    fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference()
    }
}