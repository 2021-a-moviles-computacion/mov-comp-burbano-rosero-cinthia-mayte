package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_doctor.*
import kotlinx.android.synthetic.main.activity_actualizar_paciente.*

class ActualizarDoctor : AppCompatActivity() {
    val datos =SqliteHelperExamen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_doctor)

        BasesDeDatos.TablaDoctor= SqliteHelperExamen(this)
        btn_guardar_actDoctor.setOnClickListener {
            if (BasesDeDatos.TablaDoctor!=null){
                ActualizarDoc()
            }
        }
        btn_cancelar_actDoctor.setOnClickListener {
            var intent: Intent = Intent(this, MostrarDoctor::class.java)
            startActivity(intent)
        }
    }

    fun ActualizarDoc(){
        val nombreActualizar= edit_act_nombre_doctor.text.toString()
        val edadActualizar = edit_act_edad_doctor.text.toString().toInt()
        val telefonoActualizar= edit_act_telefono_doctor.text.toString()
        val correoActualizar= edit_act_correo_doctor.text.toString()
        val idActualizar = editAct_idDoctor.text.toString().toInt()
        val especialidadAct = edit_act_especialidad.text.toString()

        if (editAct_idDoctor.text.isNotBlank()&&edit_act_nombre_doctor.text.isNotBlank()
            &&edit_act_edad_doctor.text.isNotBlank()&&edit_act_telefono_doctor.text.isNotBlank()
            &&edit_act_correo_doctor.text.isNotBlank()&&edit_act_especialidad.text.isNotBlank()){

            datos.actualizarDoctor(nombreActualizar,
            edadActualizar,telefonoActualizar,correoActualizar,especialidadAct,idActualizar)
            editAct_idDoctor.text.clear()
            edit_act_nombre_doctor.text.clear()
            edit_act_edad_doctor.text.clear()
            edit_act_telefono_doctor.text.clear()
            edit_act_correo_doctor.text.clear()
            edit_act_especialidad.text.clear()

        Log.i("Actualizar-Doctor", "${nombreActualizar} -- ${edadActualizar} -- ${idActualizar},$telefonoActualizar")
        Toast.makeText(this,"Se ha modificado", Toast.LENGTH_SHORT).show()
         }else{
        Toast.makeText(this,"Los campos no deben estar vacios", Toast.LENGTH_LONG).show()

        }

    }
}