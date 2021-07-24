package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_doctor.*
import kotlinx.android.synthetic.main.activity_actualizar_paciente.*

class ActualizarPaciente : AppCompatActivity() {
    val datos =SqliteHelperExamen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_paciente)

        BasesDeDatos.TablaPaciente= SqliteHelperExamen(this)
        btn_Actguardar_paciente.setOnClickListener {
            if (BasesDeDatos.TablaPaciente!=null){
                ActualizarPac()
            }
        }
        btn_Actcancelar_paciente.setOnClickListener {
            var intent:Intent = Intent(this, mostrarPaciente::class.java)
            startActivity(intent)
        }


    }
    fun ActualizarPac(){
        val idDoctorActualizar= edit_Act_id_doctor.text.toString().toInt()
        val nombreActualizar= edit_ActnombrePaciente.text.toString()
        val edadActualizar = edit_ActedadPaciente.text.toString().toInt()
        val telefonoActualizar= edit_Acttelf_paciente.text.toString()
        val correoActualizar= edit_ActcorreoPaciente.text.toString()
        val idActualizar = edit_id_pacienteAct.text.toString().toInt()


        if (edit_Act_id_doctor.text.isNotBlank()&&edit_ActnombrePaciente.text.isNotBlank()
            &&edit_ActedadPaciente.text.isNotBlank()&&edit_Acttelf_paciente.text.isNotBlank()
            &&edit_ActcorreoPaciente.text.isNotBlank()&&edit_id_pacienteAct.text.isNotBlank()){

            BasesDeDatos.TablaPaciente!!.actualizarPaciente(idDoctorActualizar,nombreActualizar,edadActualizar,telefonoActualizar
            ,correoActualizar,idActualizar
               )
            edit_Act_id_doctor.text.clear()
            edit_ActnombrePaciente.text.clear()
            edit_ActedadPaciente.text.clear()
            edit_Acttelf_paciente.text.clear()
            edit_ActcorreoPaciente.text.clear()
            edit_id_pacienteAct.text.clear()

            Log.i("Actualizar-Paciente", "${nombreActualizar} -- ${edadActualizar} -- ${idActualizar},$telefonoActualizar")
            Toast.makeText(this,"Se ha modificado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Los campos no deben estar vacios", Toast.LENGTH_LONG).show()

        }

    }

}