package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.DialogInterface
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mostrar_paciente.*

class MostrarPaciente : AppCompatActivity() {

    var posicionItemSeleccionado = 0
    var nombrePacienteSeleccionado = ""
    var idPacienteSeleccionado = ""
    lateinit var ArregloPacientes: ArrayList<Paciente>
    lateinit var adaptador: ArrayAdapter<Paciente>
    lateinit var listViewPaciente: ListView
    private lateinit var PacienteArrayList:ArrayList<Paciente>
    var PosisionItemPaciente =0
    companion object{
        var idPaciente=0
    }
    var adaptadorPaciente: ArrayAdapter<*>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_paciente)

        listViewPaciente = findViewById(R.id.lv_paciente)
        val pacientes = mutableListOf<Paciente>()
        val db = Firebase.firestore
        val referencia = db.collection("Paciente")
        referencia.get().addOnSuccessListener { resultado ->
            for (documento in resultado) {
                val paciente = documento.toObject(Paciente::class.java)
                paciente.idPaciente = documento.id
                paciente.nombrePaciente = documento.getString("Nombre")
                paciente.cedulaPaciente = documento.getString("Cedula")
                paciente.telefonoPaciente = documento.getString("Telefono")
                paciente.correoPaciente = documento.getString("Correo")
                paciente.idDoctor = documento.getString("IdDoctor")
                pacientes.add(paciente)
                adaptador.notifyDataSetChanged()
            }
        }.addOnFailureListener {
            Log.i("firebase-firestore", "Error leyendo coleccion")
        }
        ArregloPacientes = pacientes as ArrayList<Paciente>
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloPacientes)

        listViewPaciente.adapter = adaptador

        btn_agregar_paciente.setOnClickListener {
            val intent: Intent = Intent(this, RegistrarPaciente::class.java)
            startActivity(intent)
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
       // idPaciente = adaptadorPaciente!!.getItem(PosisionItemPaciente)!!
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {

        var adaptPaciente = adaptadorPaciente!!.getItem(PosisionItemPaciente)

        return when(item?.itemId){


            //Eliminar
            R.id.id_item_eliminar -> {
              /*  if(BasesDeDatos.TablaPaciente!=null){

                    AlertDialog.Builder(this).apply {//mensaje de alerta para confirmar si desea eliminar
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar Paciente?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BasesDeDatos.TablaPaciente!!.eliminarPaciente(adaptPaciente!!.idPaciente) //con esta linea elimino el paciente seleccionado
                            adaptadorPaciente?.remove(adaptadorPaciente!!.getItem(PosisionItemPaciente));

                        }
                        setNegativeButton("No", null)
                    }.show()


                }*/
                return true
            }

            //Editar
            R.id.id_iten_editar-> {

                if (adaptPaciente != null) {
                    // intent
                }

                return true
            }



            else -> super.onContextItemSelected(item)
        }

    }



}