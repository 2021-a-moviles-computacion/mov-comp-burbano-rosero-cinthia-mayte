package com.example.mytaller

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

class ActListView : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_list_view)

        //Arreglo  de numeros
        val arregloNumeros = BBaseDatosMemoria.arregloBEentrenador
        //Crear adaptador
        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, arregloNumeros //layout visual
        )
        val listViewEjemplo = findViewById<ListView>(R.id.Ltv_ejemplo)
        listViewEjemplo.adapter = adaptador


        val botonAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAnadirNumero.setOnClickListener {
            anadirItemALisView(
                BEentrenador("PRUEBA", "dd@d.com"),
                arregloNumeros,
                adaptador
                //  adaptador
            )
        }
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("List-view", "List-view $posicionItemSeleccionado")
        Log.i("List-view", "Entrenador ${BBaseDatosMemoria.arregloBEentrenador}")

    }

    fun anadirItemALisView(
        valor: BEentrenador,
        arreglo: ArrayList<BEentrenador>,
        adaptador: ArrayAdapter<BEentrenador>
    ) {
        arreglo.add(valor)
        adaptador.notifyDataSetChanged() //actualiza la informacion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
            return when (item?.itemId) {
                //editar
                R.id.mi_editar -> {
                    Log.i(
                        "List-view",
                        "editar ${BBaseDatosMemoria.arregloBEentrenador[posicionItemSeleccionado]}"
                    )
                    return true
                }
                //eliminar
                R.id.mi_eliminar -> {
                    Log.i(
                        "List-view",
                        "Eliminar ${BBaseDatosMemoria.arregloBEentrenador[posicionItemSeleccionado]}"
                    )
                    return true
                }
                else -> super.onContextItemSelected(item)
            }
        }
    }

