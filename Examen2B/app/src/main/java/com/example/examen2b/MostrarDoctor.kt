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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mostrar_doctor.*

class MostrarDoctor : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    var nombreDoctorSeleccionado = ""
    var idDoctorSeleccionado = ""
    lateinit var ArregloDoctores: ArrayList<Doctor>
    lateinit var adaptador: ArrayAdapter<Doctor>
    lateinit var listViewDoctor: ListView
    private lateinit var recyclerViewDoctor:RecyclerView
    private lateinit var doctorArrayList:ArrayList<Doctor>



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
               // doctor.edadDoc = documento.getString("Edad").toString().toInt()
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

        //idDodtor = adaptador!!.getItem(posicionItemSeleccionado)!!.idDoctor

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        adaptador.notifyDataSetChanged()
        val db = Firebase.firestore
        val referencia = db.collection("director")
        val directorSeleccionado = listViewDoctor.getItemAtPosition(posicionItemSeleccionado) as Doctor
        nombreDoctorSeleccionado = directorSeleccionado.nombre!!
        idDoctorSeleccionado = directorSeleccionado.idDoctor!!
        listViewDoctor.adapter = adaptador
        val cancelarClick = { _: DialogInterface, _: Int ->
            Toast.makeText(this, android.R.string.cancel, Toast.LENGTH_LONG).show()
        }
        val eliminarClick = { _: DialogInterface, _: Int ->
            Log.i("firebase", "Nombre director: $nombreDoctorSeleccionado")
             referencia.document(idDoctorSeleccionado)
                .delete()
                .addOnSuccessListener { Log.i("firebase", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.i("firebase", "Error deleting document", e) }
            ArregloDoctores.remove(directorSeleccionado)
            adaptador.notifyDataSetChanged()
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
        }
        return when (item?.itemId) {
            //editar
            R.id.id_iten_editar -> {
                abrirActividadConParametros(nombreDoctorSeleccionado, idDoctorSeleccionado, ActualizarDoctor::class.java)
                return true
            }

            //eliminar
            R.id.id_item_eliminar -> {
                val advertencia = AlertDialog.Builder(this)
                advertencia.setTitle("Eliminar")
                advertencia.setMessage("Seguro de eliminar?")
                advertencia.setNegativeButton(
                    "Cancelar",
                    DialogInterface.OnClickListener(function = cancelarClick)
                )
                advertencia.setPositiveButton(
                    "Eliminar", DialogInterface.OnClickListener(
                        function = eliminarClick
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
    private fun abrirActividad(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        startActivity(intentExplicito)
    }

    private fun abrirActividadConParametros(director: String, idDirector: String, clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombreDirector", director)
        intentExplicito.putExtra("idDirector", idDirector)
        startActivity(intentExplicito)
    }

}