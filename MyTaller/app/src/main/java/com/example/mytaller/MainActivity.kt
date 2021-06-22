package com.example.mytaller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //llamada al boton
        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonCicloVida.setOnClickListener {
            abrirCicloVida()
        }
    }
    //funcion para abrir el ciclo de vida
    fun abrirCicloVida(){
        val intentImplicito = Intent(
            this,
            MainActivityClase10::class.java
        )
        this.startActivity(intentImplicito)
    }
}