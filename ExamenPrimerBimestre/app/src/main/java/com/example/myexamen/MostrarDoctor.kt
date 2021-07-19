package com.example.myexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_mostrar_doctor.*
import kotlinx.android.synthetic.main.activity_registrar_paciente.*
import java.util.zip.Inflater

class MostrarDoctor : AppCompatActivity() {
    val datos= SqliteHelperExamen(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_doctor)
        BasesDeDatos.TablaDoctor =SqliteHelperExamen(this)
        btn_agregar_doctorIn.setOnClickListener {
            val intent = Intent(this, registrarDoctor::class.java)
            startActivity(intent)
        }
        val arregloDoctor: ArrayList<String> = arrayListOf()
        arregloDoctor.add("Cinthia")
        //val arregloDoctor = datos.mostrarDoctor()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloDoctor)
        val listViewDoctor = findViewById<ListView>(R.id.id_lista_doctor)
        listViewDoctor.adapter = adaptador

        listViewDoctor.setOnItemLongClickListener { parent, view, position, id ->
            Log.i("list-view", "Dio click ${position}")
            return@setOnItemLongClickListener true
        }
        //val arregloDoctor = datos.mostrarDoctor()
        //val adaptador: ArrayAdapter<doctorBDD> = ArrayAdapter(this,android.R.layout.simple_list_item_1,arregloDoctor)


       // val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloDoctor)
        //val listViewDoctor = findViewById<ListView>(R.id.id_lista_doctor)
        //listViewDoctor.adapter = adaptador
        //listViewDoctor.setOnItemClickListener { adaterView, view, posicion, id ->
           // Log.i("list-view", "Dio click ${posicion}")
         //   return@setOnItemClickListener
       // }
        val listItems:ListView
        //registerForContextMenu(listItems)
    }



        fun EliminarDoctor(){


        }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

       val ide= v?.getId()
        val menuInflete = menuInflater
        if(ide == R.id.id_lista_doctor){
            menuInflete.inflate(R.menu.menu,menu)
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }
}