package com.example.examen2b

import android.app.AlertDialog
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
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mostrar_doctor.*

class MostrarDoctor : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var posicionItemSeleccionado = 0
    var nombreDoctorSeleccionado = ""
    var idDoctorSeleccionado = ""
    var edadDoctorSeleccionado = ""
    var correoDoctorSeleccionado = ""
    var telefonoItemSSeleccionado = ""
    var especialidadItemSeleccionado = ""
    lateinit var ArregloDoctores: ArrayList<Doctor>
    lateinit var adaptador: ArrayAdapter<Doctor>
    lateinit var listViewDoctor: ListView
    private lateinit var recyclerViewDoctor:RecyclerView
    private lateinit var doctorArrayList:ArrayList<Doctor>
    companion object{
        var id_D = ""
        var nombre = "Doctor"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_doctor)

        listViewDoctor = findViewById(R.id.lv_doctor)
        val doctores = mutableListOf<Doctor>()
        val db = Firebase.firestore
        val referencia = db.collection("Doctor")
        referencia.get().addOnSuccessListener { resultado ->
            for (documento in resultado) {
                val doctor = documento.toObject(Doctor::class.java)
                doctor.idDoctor = documento.id
                doctor.nombre = documento.getString("Nombre")
                doctor.cedulaDoc = documento.getString("Cedula")
                doctor.edadDoc = documento.getLong("Edad").toString().toInt()
                doctor.telefonoDoc = documento.getString("Telefono")
                doctor.correoDoc = documento.getString("Correo")
                doctor.especialidad = documento.getString("Especialidad")
                doctores.add(doctor)
                adaptador.notifyDataSetChanged()
            }
        }.addOnFailureListener {
            Log.i("firebase-firestore", "Error leyendo coleccion")
        }
        ArregloDoctores = doctores as ArrayList<Doctor>
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloDoctores)

        listViewDoctor.adapter = adaptador

        btn_agregar_doctorIn.setOnClickListener {
            val inten: Intent = Intent(this, RegistrarDoctor::class.java)
            startActivity(inten)
        }
        registerForContextMenu(lv_doctor)


    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        adaptador.notifyDataSetChanged()
        var idElemento = ArregloDoctores[posicionItemSeleccionado]

        val db = Firebase.firestore
        val referencia = db.collection("Doctor")
        val doctorSeleccionado = listViewDoctor.getItemAtPosition(posicionItemSeleccionado) as Doctor
        nombreDoctorSeleccionado = doctorSeleccionado.nombre!!
        idDoctorSeleccionado = doctorSeleccionado.idDoctor!!
        edadDoctorSeleccionado = doctorSeleccionado.edadDoc.toString()
        telefonoItemSSeleccionado= doctorSeleccionado.telefonoDoc.toString()
        correoDoctorSeleccionado = doctorSeleccionado.correoDoc.toString()
        especialidadItemSeleccionado= doctorSeleccionado.especialidad.toString()

        listViewDoctor.adapter = adaptador
        val cancelarClick = { _: DialogInterface, _: Int ->
            Toast.makeText(this, android.R.string.cancel, Toast.LENGTH_LONG).show()
        }
        val eliminarDoc = { _: DialogInterface, _: Int ->
            Log.i("firebase", "Nombre: $nombreDoctorSeleccionado")
            referencia.document(idDoctorSeleccionado)
                .delete()
                .addOnSuccessListener { Log.i("firebase", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.i("firebase", "Error deleting document", e) }
            ArregloDoctores.remove(doctorSeleccionado)
            adaptador.notifyDataSetChanged()
            Toast.makeText(this, "Eliminado", Toast.LENGTH_LONG).show()
        }
        return when (item.itemId) {
            //editar
            R.id.id_iten_editar -> {
                abrirActividadporId(ActualizarDoctor::class.java, idElemento)
                return true
            }

            //eliminar
            R.id.id_item_eliminar -> {
                val advertencia = AlertDialog.Builder(this)
                advertencia.setTitle("Eliminar")
                advertencia.setMessage("Seguro que desea eliminar?")
                advertencia.setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener(function = cancelarClick)
                )
                advertencia.setPositiveButton(
                    "Si", DialogInterface.OnClickListener(

                        function = eliminarDoc
                    )
                )
                advertencia.show()
                return true
            }
            //MOSTRAR PACIENTE
            R.id.id_itemverPaciente-> {

                /*if (aDoctor != null) {
                    abrirActividad(mostrarPacientePorDoctor::class.java)
                }*/
                return true }

            else -> super.onContextItemSelected(item)
        }

    }

    fun abrirActividadporId(
        clase: Class<*>,
        doctor: Doctor
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("Doctor", doctor)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


}