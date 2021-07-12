package com.example.moviles_computacion_2021_b

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Usuario : AppCompatActivity() {
    private lateinit var edNombre: EditText
    private lateinit var edDescripcion: EditText
    private lateinit var edId: EditText
    private lateinit var btnAdd:Button
    private lateinit var btnVer:Button
    private lateinit var btnBorrar :Button
    private lateinit var btnEditar :Button

    private lateinit var sqliteHelper: ESqliteHelperUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        ver()
        sqliteHelper = ESqliteHelperUsuario(this)

        btnAdd.setOnClickListener{añadirUsuario()}
        btnVer.setOnClickListener{obtenerUsuario()}
        btnEditar.setOnClickListener{  editarUsuario() }
        btnBorrar.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("Alerta")
                setMessage("¿Desea eliminar?")
                setPositiveButton("Si"){ _:DialogInterface, _: Int ->
                    eliminarUsuario()
                }
                setNegativeButton("No", null)
            }.show()
            }
    }
    fun eliminarUsuario(){
        val id = edId.getText().toString().toInt()
        val mensaje = sqliteHelper.eliminarUsuarioFormulario(id)
        Log.i("Eliminar","${id}")
        Toast.makeText(this,"usuario eliminado", Toast.LENGTH_SHORT).show()

    }

    fun editarUsuario(){
        var nombre = edNombre.text.toString()
        var descripcion = edDescripcion.text.toString()
        var id = edId.text.toString().toInt()

    if(edNombre.text.isNotBlank()&&edDescripcion.text.isNotBlank() && edId.text.isNotBlank()){
        sqliteHelper.actualizarUsuarioFormulario(nombre,
            descripcion, id)
        edNombre.text.clear()
        edDescripcion.text.clear()
        Log.i("Actualizar", "${nombre} -- ${descripcion} -- ${id}")
        borrarTexto()

        Toast.makeText(this,"Se ha modificado", Toast.LENGTH_SHORT).show()
    }else{
        Toast.makeText(this,"Los campos no deben estar vacios", Toast.LENGTH_LONG).show()

    }
    }

    fun obtenerUsuario(){
        val id = edId.getText().toString().toInt()
        val lista=sqliteHelper.consultarUsuarioPorId(id)
        edNombre.setText(lista.nombre)
        edDescripcion.setText(lista.descripcion)
        Log.i("Consultar Usuario", "${id}")
        if(edNombre.text.isNotBlank() && edDescripcion.text.isNotBlank()){

        }else{
            Toast.makeText(this,"No existen el Usuario", Toast.LENGTH_LONG).show()

        }

        //edId.setText("")

    }

    private fun añadirUsuario(){
        val nombre = edNombre.text.toString()
        val descripcion = edDescripcion.text.toString()

        if(nombre.isEmpty() || descripcion.isEmpty()){
            Toast.makeText(this,"ingrese el requerimiento", Toast.LENGTH_SHORT).show()
        }else{
            val estado = sqliteHelper.crearUsuarioFormulario(nombre, descripcion)

            if (estado != null){
                Toast.makeText(this,"usuario añadido", Toast.LENGTH_SHORT).show()
                borrarTexto()
                Log.e("añadir","${nombre} ---> ${descripcion}")
            }else{
                Toast.makeText(this,"usuario no añadido", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun borrarTexto(){
        edNombre.setText("")
        edDescripcion.setText("")
        edId.setText("")
        edNombre.requestFocus()
    }

    private fun ver(){
        edNombre = findViewById(R.id.edNombre)
        edDescripcion = findViewById(R.id.edDescripcion)
        edId = findViewById(R.id.edId)
        btnAdd=findViewById(R.id.btn_add)
        btnVer=findViewById(R.id.btn_ver)
        btnBorrar=findViewById(R.id.btn_Borrar)
        btnEditar=findViewById(R.id.btn_editar)
    }
}