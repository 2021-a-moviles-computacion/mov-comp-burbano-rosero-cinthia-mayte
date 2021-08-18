package com.example.mytaller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_base_de_datos.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.editTextdesc_actualizar
import kotlinx.android.synthetic.main.activity_main.edti_id

class MainActivity : AppCompatActivity() {
    val  CODIGO_RESPUESTA_INTENT_EXPLICITO =401
    val  CODIGO_RESPUESTA_INTENT_IMPLICITO =402
    val datos = ESqliteHelperUsuari(this)
    private lateinit var edNombre: EditText
    private lateinit var edDescripcion: EditText
    private lateinit var edId: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuari(this)

        btn_anadirUsuario.setOnClickListener {
            val intent = Intent(this, ActiAgregarUsuario::class.java)
            startActivity(intent)
        }
        btn_consultarUsuario.setOnClickListener {
                       obtenerUsuario()
        }
        btn_actualizarUsuario.setOnClickListener {

            if (EBaseDeDatos.TablaUsuario != null) {
                ActualizarUsuario()
                editTex_nombre.setText("")
                editTextdesc_actualizar.setText("")
                edti_id.setText("")
            }
        }
        btn_eliminarUsuario.setOnClickListener {

            if (EBaseDeDatos.TablaUsuario != null) {
                eliminarUsuario()
                edti_id.setText("")
                editTex_nombre.setText("")
                editTextdesc_actualizar.setText("")
            }
        }
    }

    fun eliminarUsuario() {

        val id = edti_id.getText().toString().toInt()
        val mensaje = datos.eliminarUsuarioFormulario(id)
        Log.i("Eliminar-Usuario","${id}")
        Toast.makeText(this,"Usuario eliminado", Toast.LENGTH_SHORT).show()
    }
    fun ActualizarUsuario(editTextdesc_actualizar: Any) {
        var nombre = editTex_nombre.text.toString()
        var descripcion = editTextdesc_actualizar.text.toString()
        var id = edti_id.text.toString().toInt()

        if(editTex_nombre.text.isNotBlank()&&editTextdesc_actualizar.text.isNotBlank() && edti_id.text.isNotBlank()){
            datos.actualizarUsuarioFormulario(nombre,
                descripcion, id)
            editTex_nombre.text.clear()
            editTextdesc_actualizar.text.clear()
            Log.i("Actualizar-Usuarui", "${nombre} -- ${descripcion} -- ${id}")
            Toast.makeText(this,"Se ha modificado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Los campos no deben estar vacios", Toast.LENGTH_LONG).show()

        }
    }
    fun obtenerUsuario(){
        val id = edti_id.getText().toString().toInt()
        val lista = datos.consultarUsuarioPorId(id)
        editTex_nombre.setText(lista.nombre)
        editTextdesc_actualizar.setText(lista.descripcion)
        Log.i("Consultar-Usuario", "$id")
        if (editTex_nombre.text.isNotBlank() && editTextdesc_actualizar.text.isNotBlank()) {
            Toast.makeText(this, " Usuario encontrado", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this, "No existen el Usuario", Toast.LENGTH_LONG).show()

        }

    }
}
