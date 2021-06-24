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
        val botonListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonListView.setOnClickListener {
            abrirActividad(ActListView::class.java)
        }

        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonCicloVida.setOnClickListener {
            abrirActividad(ActListView::class.java)
        }
    }
    //funcion para abrir el ciclo de vida
    fun abrirActividad(
        clase:Class<*>
    ){
        val intentImplicito = Intent(
            this,
            clase
                    )
        this.startActivity(intentImplicito)
    }

}