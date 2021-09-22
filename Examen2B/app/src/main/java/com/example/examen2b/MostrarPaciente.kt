package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.DialogInterface
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mostrar_doctor.*
import kotlinx.android.synthetic.main.activity_mostrar_paciente.*

class MostrarPaciente : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var posicionItemSeleccionado = 0
    var nombrePacienteSeleccionado = ""
    var idPacienteSeleccionado = ""
    var idDoctorPacienteSeleccionado =""
    var edadPacienteSeleccionado = 0
    var telefonoItemSSeleccionado= ""
    var correoItemSeleccionado = ""
    var cedulaItemSeleccionado= ""
    var longitudItemSeleccionado= 0.0
    var LatitudItemSeleccionado= 0.0
    lateinit var ArregloPacientes: ArrayList<Paciente>
    lateinit var adaptador: ArrayAdapter<Paciente>
    lateinit var listViewPaciente: ListView
    private lateinit var PacienteArrayList:ArrayList<Paciente>

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
                paciente.edadPaciente = documento.getLong("Edad")!!.toInt()
                paciente.telefonoPaciente = documento.getString("Telefono")
                paciente.longitud = documento.getDouble("Longitud")
                paciente.latitud = documento.getDouble("Latitud")
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
        registerForContextMenu(lv_paciente)

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
        posicionItemSeleccionado = info.position

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        adaptador.notifyDataSetChanged()
        var idElemento = ArregloPacientes[posicionItemSeleccionado]

        val db = Firebase.firestore
        val referencia = db.collection("Paciente")
        val pacienteSeleccionado = listViewPaciente.getItemAtPosition(posicionItemSeleccionado) as Paciente
        nombrePacienteSeleccionado = pacienteSeleccionado.nombrePaciente.toString()
        idPacienteSeleccionado = pacienteSeleccionado.idPaciente.toString()
        idDoctorPacienteSeleccionado = pacienteSeleccionado.idDoctor.toString()
       // edadPacienteSeleccionado = pacienteSeleccionado.edadPaciente.toString().toInt()
        telefonoItemSSeleccionado= pacienteSeleccionado.telefonoPaciente.toString()
        correoItemSeleccionado = pacienteSeleccionado.correoPaciente.toString()
        cedulaItemSeleccionado= pacienteSeleccionado.cedulaPaciente.toString()
        longitudItemSeleccionado= pacienteSeleccionado.longitud.toString().toDouble()
        LatitudItemSeleccionado= pacienteSeleccionado.latitud.toString().toDouble()


        listViewPaciente.adapter = adaptador

        val cancelarClick = { _: DialogInterface, _: Int ->
            Toast.makeText(this, android.R.string.cancel, Toast.LENGTH_LONG).show()
        }
        val eliminarPaciente = { _: DialogInterface, _: Int ->
            Log.i("firebase", "Nombre: $nombrePacienteSeleccionado")
            referencia.document(idPacienteSeleccionado)
                .delete()
                .addOnSuccessListener { Log.i("firebase", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.i("firebase", "Error deleting document", e) }
            ArregloPacientes.remove(pacienteSeleccionado)
            adaptador.notifyDataSetChanged()
            Toast.makeText(this, "Paciente eliminado", Toast.LENGTH_LONG).show()
        }

        return when(item.itemId){


            //Eliminar
            R.id.id_item_eliminar -> {
                val advertencia = android.app.AlertDialog.Builder(this)
                advertencia.setTitle("Eliminar")
                advertencia.setMessage("Seguro que desea eliminar?")
                advertencia.setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener(function = cancelarClick)
                )
                advertencia.setPositiveButton(
                    "Si", DialogInterface.OnClickListener(

                        function = eliminarPaciente
                    )
                )
                advertencia.show()
                return true
            }
            //Editar
            R.id.id_iten_editar-> {

                abrirActividadporId(ActualizarPaciente::class.java, idElemento)
                return true
            }
            //mostrar Ubicacion
            R.id.id_ubicacion ->{
                abrirActiviadUbicacion(Ubicacion::class.java, idElemento)

                return true
            }

            else -> super.onContextItemSelected(item)
        }

    }

    fun abrirActividadporId(
        clase: Class<*>,
        paciente: Paciente
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("Paciente", paciente)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
    fun abrirActiviadUbicacion(
        clase: Class<*>,
        paciente: Paciente
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("Paciente", paciente)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}