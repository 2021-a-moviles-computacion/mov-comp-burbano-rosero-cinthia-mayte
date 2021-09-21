package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_actualizar_paciente.*

class ActualizarPaciente : AppCompatActivity() {
    var idPaciente: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_paciente)

        val paciente = intent.getParcelableExtra<Paciente>("Paciente")
        idPaciente = paciente!!.idPaciente

        val idDoctorActualizar= edit_Act_id_doctor.text.toString()
        val nombreActualizar= edit_ActnombrePaciente.text.toString()
        val edadActualizar = edit_ActedadPaciente.text.toString().toInt()
        val telefonoActualizar= edit_Acttelf_paciente.text.toString()
        val correoActualizar= edit_ActcorreoPaciente.text.toString()
        val longitudActualizar = edit_act_long.text.toString().toDouble()
        val latitudActualizar =edit_act_latitud.text.toString().toDouble()
        val cedulaActualizar = edit_ced_act.text.toString()

        val botonActualizarPac = findViewById<Button>(R.id.btn_guardar_actDoctor)
        botonActualizarPac.setOnClickListener {
            val actualizarPaciente = hashMapOf<String, Any>(
                "IdDoctor" to idDoctorActualizar,
                "Nombre" to nombreActualizar,
                "Cedula" to cedulaActualizar,
                "Edad" to edadActualizar,
                "Telefono" to telefonoActualizar,
                "Correo" to correoActualizar,
                "Longitud" to longitudActualizar,
                "Latitud" to latitudActualizar,

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
}