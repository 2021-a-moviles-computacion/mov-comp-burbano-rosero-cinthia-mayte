package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mostrar_paciente.*

class mostrarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_paciente)
        btn_agregar_paciente.setOnClickListener {
            val inten:Intent =Intent(this, registrarPaciente::class.java)
            startActivity(inten)
        }
    }
}