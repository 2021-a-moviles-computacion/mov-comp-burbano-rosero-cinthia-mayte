package com.example.examen2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_actualizar_paciente.*

class ActualizarPaciente : AppCompatActivity() {
    var idPaciente: String? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_paciente)

        val paciente = intent.getParcelableExtra<Paciente>("Paciente")
        idPaciente = paciente!!.idPaciente

        val idDoctorActualizar= findViewById<EditText>(R.id.edit_Act_id_doctor)

        val nombreActualizar=findViewById<EditText>(R.id.edit_ActnombrePaciente)
        val edadActualizar =  findViewById<EditText>(R.id.edit_ActedadPaciente)
        val telefonoActualizar=findViewById<EditText>(R.id.edit_Acttelf_paciente)
        val correoActualizar= findViewById<EditText>(R.id.edit_ActcorreoPaciente)
        val longitudActualizar =findViewById<EditText>(R.id.edit_act_long)
        val latitudActualizar =findViewById<EditText>(R.id.edit_act_latitud)
        val cedulaActualizar = findViewById<EditText>(R.id.edit_ced_act)

        val botonActualizarPac = findViewById<Button>(R.id.btn_Actguardar_paciente)
        botonActualizarPac.setOnClickListener {
            val actualizarPaciente = hashMapOf<String, Any>(
                "IdDoctor" to idDoctorActualizar.text.toString(),
                "Nombre" to nombreActualizar.text.toString(),
                "Cedula" to cedulaActualizar.text.toString(),
                "Edad" to edadActualizar.text.toString().toInt(),
                "Telefono" to telefonoActualizar.text.toString(),
                "Correo" to correoActualizar.text.toString(),
                "Longitud" to longitudActualizar.text.toString().toDouble(),
                "Latitud" to latitudActualizar.text.toString().toDouble(),

            )
            val db = FirebaseFirestore.getInstance()
            db.collection("Paciente")
                .document(idPaciente!!)
                .set(
                    actualizarPaciente
                )
                .addOnSuccessListener {
                    edit_ActnombrePaciente.setText("")
                    edit_ActedadPaciente.setText("")
                    edit_Acttelf_paciente.setText("")
                    edit_Act_id_doctor.setText("")
                    edit_ActcorreoPaciente.setText("")
                    edit_act_long.setText("")
                    edit_act_latitud.setText("")
                    edit_ced_act.setText("")
                    Toast.makeText(this,"Paciente actualizado con exito", Toast.LENGTH_LONG).show()
                }
            abrirActividad(MostrarPaciente::class.java)

        }
        val botonCancelar = findViewById<Button>(R.id.btn_Actcancelar_paciente)
        botonCancelar.setOnClickListener {
            abrirActividad(MostrarPaciente::class.java)
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
