package com.example.myexamen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registrar_doctor.*
import kotlinx.android.synthetic.main.activity_main.*


class registrarDoctor : AppCompatActivity() {

    val datos = SqliteHelperExamen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_doctor)
        BasesDeDatos.TablaDoctor= SqliteHelperExamen(this)
        btn_registrar_doctor.setOnClickListener {
            if(BasesDeDatos.TablaDoctor!=null){
                agregarDoctor()
                editText_cedula_doctor.setText("")
                editText_nombre_doctor.setText("")
                editText_edad_doctor.setText("")
                editText_correo_doctor.setText("")
                editText_telefono_doctor.setText("")
                editText_especialidad.setText("")
            }
        }
        btn_cancelar_doctor.setOnClickListener {
            val intent:Intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun agregarDoctor(){

        val editCedula= editText_cedula_doctor.text.toString()
        val editNombre= editText_nombre_doctor.text.toString()
        val editEdad = editText_edad_doctor.text.toString().toInt()
        val editTelefonod= editText_telefono_doctor.text.toString()
        val editCorreo= editText_correo_doctor.text.toString()
        val editEspecialidad= editText_especialidad.text.toString()
        val lista = datos.agregarDoctor(editCedula,editNombre,editEdad,editTelefonod,editCorreo,editEspecialidad)

        if(lista != null){
            Toast.makeText(this,"Doctor guardado correctamente", Toast.LENGTH_SHORT).show()
            Log.i("anadir-doctor","${editCedula} ---> ${editNombre}, $editEdad, $editTelefonod, $editCorreo,$editEspecialidad")
        }else{
            Toast.makeText(this,"Usuario no Ingresado", Toast.LENGTH_SHORT).show()

        }
    }
}