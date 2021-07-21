package com.example.myexamen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_mostrar_paciente.*

class mostrarPaciente : AppCompatActivity() {
    companion object{
        var idPaciente=0
    }
    var PosisionItemPaciente =0
    var adaptadorPaciente: ArrayAdapter<PacienteBDD>?=null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_paciente)
        btn_agregar_paciente.setOnClickListener {
            val inten:Intent =Intent(this, registrarPaciente::class.java)
            startActivity(inten)
        }
        if(BasesDeDatos.TablaPaciente != null) {
            val Paciente = BasesDeDatos.TablaPaciente !!.mostrarPaciente()
            adaptadorPaciente= ArrayAdapter(this, android.R.layout.simple_list_item_1,Paciente)
            val listViewMostrarPaciente = findViewById<ListView>(R.id.id_lista_paciente)
            listViewMostrarPaciente.adapter = adaptadorPaciente
            registerForContextMenu(listViewMostrarPaciente)

        }
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menupaciente,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        PosisionItemPaciente = id
        idPaciente = adaptadorPaciente!!.getItem(PosisionItemPaciente)!!.idPaciente
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {

        var adaptPaciente = adaptadorPaciente!!.getItem(PosisionItemPaciente)

        return when(item?.itemId){


            //Eliminar
            R.id.id_item_eliminarPaciente -> {
                if(BasesDeDatos.TablaPaciente!=null){

                    AlertDialog.Builder(this).apply {
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar Paciente?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BasesDeDatos.TablaPaciente!!.eliminarPaciente(adaptPaciente!!.idPaciente)
                            adaptadorPaciente?.remove(adaptadorPaciente!!.getItem(PosisionItemPaciente));

                        }
                        setNegativeButton("No", null)
                    }.show()


                }
                return true
            }

            //Editar
            R.id.id_item_editarPaciente-> {

                if (adaptPaciente != null) {
                    abrirActividadConParametros(ActualizarPaciente::class.java,adaptPaciente)
                }

                return true
            }


            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirActividadConParametros(clase: Class<*>, paciente: PacienteBDD, ) {
        val intenExplicito =Intent(this, clase)
        intenExplicito.putExtra("paciente",paciente)
        startActivityForResult(intenExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
}