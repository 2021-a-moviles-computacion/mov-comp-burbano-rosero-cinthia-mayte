package com.example.myexamen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class mostrarPacientePorDoctor : AppCompatActivity() {
    var adapterPac: ArrayAdapter<PacienteBDD>? = null
    var CODIGO_RESPUESTA_INTENT_EXPLICITO = 402
    var CODIGO_RESPUESTA_INTENT_EXPLICITO3 = 405
    var posicionItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_paciente_por_doctor)
        val Doctor= intent.getParcelableExtra<PacienteBDD>("Paciente")
        val id = MostrarDoctor.idDodtor


        BasesDeDatos.TablaPaciente= SqliteHelperExamen(this)

        if(BasesDeDatos.TablaPaciente!= null) {
            val Paciente = BasesDeDatos.TablaPaciente !!.consultarPacientePorDoctor(id)
            adapterPac= ArrayAdapter(this, android.R.layout.simple_list_item_1,Paciente)
            val listViewMostrarPaciente = findViewById<ListView>(R.id.lv_pacientes)
            listViewMostrarPaciente.adapter = adapterPac
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
        posicionItem = id
        mostrarPaciente.idPaciente = adapterPac!!.getItem(posicionItem)!!.idPaciente
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {

        var adaptPaciente = adapterPac!!.getItem(posicionItem)

        return when(item?.itemId){


            //Eliminar
            R.id.id_item_eliminarPaciente -> {
                if(BasesDeDatos.TablaPaciente!=null){

                    AlertDialog.Builder(this).apply {//mensaje de alerta para confirmar si desea eliminar
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar Paciente?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BasesDeDatos.TablaPaciente!!.eliminarPaciente(adaptPaciente!!.idPaciente) //con esta linea elimino el paciente seleccionado
                            adapterPac?.remove(adapterPac!!.getItem(posicionItem));

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
        val intenExplicito = Intent(this, clase)
        intenExplicito.putExtra("paciente",paciente)
        startActivityForResult(intenExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
}