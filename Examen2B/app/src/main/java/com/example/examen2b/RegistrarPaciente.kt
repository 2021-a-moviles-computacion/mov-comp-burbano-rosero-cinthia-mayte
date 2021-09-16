package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistrarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_paciente)
        val botonCancelarRPaciente=findViewById<Button>(R.id.btn_cancelar_paciente)
        botonCancelarRPaciente.setOnClickListener {
            val intent: Intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}