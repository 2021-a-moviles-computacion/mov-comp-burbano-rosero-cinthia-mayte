package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonDoctor = findViewById<Button>(R.id.btn_ir_doctor)
        botonDoctor.setOnClickListener {
            val intent: Intent = Intent(this, MostrarDoctor::class.java)
            startActivity(intent)
        }
        val botonPaciente = findViewById<Button>(R.id.btn_ir_paciente)
        botonPaciente.setOnClickListener {
            val intent : Intent = Intent(this, MostrarPaciente::class.java)
            startActivity(intent)
        }
    }
}