package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ActualizarDoctor : AppCompatActivity() {
    var idDoctor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_doctor)
        val doctor = intent.getParcelableExtra<Doctor>("Doctor")
        idDoctor = doctor!!.idDoctor

        val nombreDoctor = findViewById<EditText>(R.id.edit_act_nombre_doctor)
        val cedulaDoctor = findViewById<EditText>(R.id.edit_cedula_act)
        val edadDoctor = findViewById<EditText>(R.id.edit_act_edad_doctor)
        val telefonoDoctor = findViewById<EditText>(R.id.edit_act_telefono_doctor)
        val correoDoctor = findViewById<EditText>(R.id.edit_act_correo_doctor)
        val especialidadDoctor = findViewById<EditText>(R.id.edit_act_especialidad)

        val botonActualizarDoc = findViewById<Button>(R.id.btn_guardar_actDoctor)
        botonActualizarDoc.setOnClickListener {
            val actualizarDoctor = hashMapOf<String, Any>(
                "Nombre" to nombreDoctor.text.toString(),
                "Cedula" to cedulaDoctor.text.toString(),
                "Edad" to edadDoctor.text.toString().toInt(),
                "Telefono" to telefonoDoctor.text.toString(),
                "Correo" to correoDoctor.text.toString(),
                "Especialidad" to especialidadDoctor.text.toString()
            )
            val db = FirebaseFirestore.getInstance()
            db.collection("Doctor")
                .document(idDoctor!!)
                .set(
                    actualizarDoctor
                )
                .addOnSuccessListener {
                    nombreDoctor.setText("")
                    edadDoctor.setText("")
                    telefonoDoctor.setText("")
                    correoDoctor.setText("")
                    especialidadDoctor.setText("")
                    cedulaDoctor.setText("")
                    Toast.makeText(this,"Doctor actualizado con exito",Toast.LENGTH_LONG).show()
                }
            abrirActividad(MostrarDoctor::class.java)

        }
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_actDoctor)
        botonCancelar.setOnClickListener {
            abrirActividad(MostrarDoctor::class.java)
        }
    }
    fun abrirActividad(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}