package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_doctor.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val datos =SqliteHelperExamen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_ir_doctor.setOnClickListener {
           val  intent: Intent = Intent(this, MostrarDoctor::class.java)
            startActivity(intent)
            }
       btn_ir_paciente.setOnClickListener {
            val  intent: Intent = Intent(this, mostrarPaciente::class.java)
            startActivity(intent)
        }

    }
}
