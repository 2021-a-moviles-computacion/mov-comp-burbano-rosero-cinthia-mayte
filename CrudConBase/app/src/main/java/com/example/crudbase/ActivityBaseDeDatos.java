package com.example.crudbase;

import android.app.Activity;

class ActivityBaseDeDatos : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_de_datos)

        var botonMas = findViewById<Button>(R.id.btn_añadirUsuario)
        botonMas.setOnClickListener {


        }
        }
        }
