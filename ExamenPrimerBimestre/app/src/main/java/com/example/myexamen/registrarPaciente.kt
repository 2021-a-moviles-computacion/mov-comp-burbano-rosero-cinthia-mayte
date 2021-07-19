package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar_paciente.*

class registrarPaciente : AppCompatActivity() {
    val datos = SqliteHelperExamen(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_paciente)

            BasesDeDatos.TablaPaciente= SqliteHelperExamen(this)


            btn_guardar_paciente.setOnClickListener {
                if(BasesDeDatos.TablaPaciente!=null){
                    agregarPaciente()
                    edit_id_doctor.setText("")
                    edit_cedulaPaciente.setText("")
                    edit_nombrePaciente.setText("")
                    edit_edadPaciente.setText("")
                    edit_telf_paciente.setText("")
                    edit_correoPaciente.setText("")
                }
            }
        btn_cancelar_paciente.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        }
        fun agregarPaciente(){
            val editIdDoctor= edit_id_doctor.text.toString().toInt()
            val editCedulaPac= edit_cedulaPaciente.text.toString()
            val editNombrePac= edit_nombrePaciente.text.toString()
            val editEdadPac= edit_edadPaciente.text.toString().toInt()
            val editTelefonodPac= edit_telf_paciente.text.toString()
            val editCorreoPac= edit_correoPaciente.text.toString()
            val lista = datos.agregarPaciente(editIdDoctor,editCedulaPac,editNombrePac,editEdadPac,editTelefonodPac,editCorreoPac)
            if(lista!=null){
                Toast.makeText(this,"Paciente guardado correctamente", Toast.LENGTH_SHORT).show()
                Log.i("añadirPaciente","$editIdDoctor,${editCedulaPac} ---> ${editNombrePac}, $editEdadPac,$editTelefonodPac,$editCorreoPac")
            }else{
                Toast.makeText(this,"Paciente no Ingresado", Toast.LENGTH_SHORT).show()

            }
        }

}