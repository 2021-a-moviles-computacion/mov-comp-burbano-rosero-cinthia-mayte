package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_doctor.*
import kotlinx.android.synthetic.main.activity_actualizar_paciente.*

class ActualizarDoctor : AppCompatActivity() {
    val datos =SqliteHelperExamen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_doctor)
        btn_cancelar_actDoctor.setOnClickListener {
            val inten:Intent =Intent(this, MostrarDoctor::class.java)
            startActivity(inten)
        }

        val nombreDoctor = findViewById<EditText>(R.id.edit_act_nombre_doctor)
        val edadDoctor = findViewById<EditText>(R.id.edit_act_edad_doctor)
        val telefonoDoctor = findViewById<EditText>(R.id.edit_act_telefono_doctor)
        val correoDoctor = findViewById<EditText>(R.id.edit_act_correo_doctor)
        val especialidadDoctor = findViewById<EditText>(R.id.edit_act_especialidad)

        BasesDeDatos.TablaDoctor= SqliteHelperExamen(this)

        btn_guardar_actDoctor.setOnClickListener {
            val nombre= nombreDoctor.text.toString()
            val edad = edadDoctor.text.toString().toInt()
            val tel =telefonoDoctor.text.toString()
            val correo= correoDoctor.text.toString()
            val espec= especialidadDoctor.text.toString()
            val id_doctor=editAct_idDoctor.text.toString().toInt()

            if(nombreDoctor.text.isNotBlank()&&edadDoctor.text.isNotBlank()&&telefonoDoctor.text.isNotBlank()
                &&correoDoctor.text.isNotBlank()&&especialidadDoctor.text.isNotBlank()){
                BasesDeDatos.TablaDoctor!!.actualizarDoctor(nombre,edad,tel,correo,espec,id_doctor)
                edit_act_nombre_doctor.setText("")
                edit_act_edad_doctor.setText("")
                edit_act_telefono_doctor.setText("")
                edit_act_correo_doctor.setText("")
                edit_act_especialidad.setText("")
                Log.i("Actualizar-Doctor", "${nombre} -- ${edad} -- ${tel},$espec")
                Toast.makeText(this,"Se ha modificado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Los campos no deben estar vacios", Toast.LENGTH_LONG).show()
            }
        }



    }
}