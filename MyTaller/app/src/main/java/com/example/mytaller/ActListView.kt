package com.example.mytaller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class ActListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_list_view)

        //Arreglo  de numeros
        val arregloNumeros = arrayListOf<Int>(1, 2, 3, 4, 5)
        //Crear adaptador
        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, arregloNumeros //layout visual
        )
        val listViewEjemplo = findViewById<ListView>(R.id.Ltv_ejemplo)
        listViewEjemplo.adapter = adaptador


        val botonAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAnadirNumero.setOnClickListener {
            anadirItemALisView(
                1,
                arregloNumeros,
                adaptador
              //  adaptador
            )}
   /*     listViewEjemplo.setOnItemLongClickListener { adapterView, view, posicion, id ->
            Log.i("list-view","Dio clic ${posicion}")
            return@setOnItemLongClickListener true
        }*/
        registerForContextMenu(listViewEjemplo)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
       // val info = menuInfo as AdapterView.AdapterContextMenuInfo
        //val id = info.position
        //posicionItemSeleccionado =id
    }

        fun anadirItemALisView( valor:Int,
                                arreglo: ArrayList<Int>,
                                adaptador:ArrayAdapter<Int>){
            arreglo.add(valor)
            adaptador.notifyDataSetChanged() //actualiza la informacion
        }
    }
