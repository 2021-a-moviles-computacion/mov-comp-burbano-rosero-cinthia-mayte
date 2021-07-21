package com.example.mytaller

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_acti_agregar_usuario.*
import kotlinx.android.synthetic.main.activity_main.*

class ActiAgregarUsuario : AppCompatActivity() { 
    val datos = ESqliteHelperUsuari(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_agregar_usuario)
        EBaseDeDatos.TablaUsuario= ESqliteHelperUsuari(this)
        EBaseDeDatos.TablaUsuario= ESqliteHelperUsuari(this)
        btn_nuevo_usuario.setOnClickListener {
            //val txtNombreAAgregar = editTextnonbre_agregar.getText().toString()
            //val txtdescAAgregar= editTextDescri_agregar.getText().toString()
            if(EBaseDeDatos.TablaUsuario!=null){
                agregar()
                //EBaseDeDatos.TablaUsuario!!.crearUsuarioFormulario(txtNombreAAgregar,txtdescAAgregar)
                   editTextnonbre_agregar.setText("")
                   editTextDescri_agregar.setText("")
            }

        }

        btn_regresar.setOnClickListener {
            val intent:Intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun agregar(){
        val txtNombreAAgregar = editTextnonbre_agregar.text.toString()
        val txtdescAAgregar= editTextDescri_agregar.text.toString()
        val lista = datos.crearUsuarioFormulario(txtNombreAAgregar,txtdescAAgregar)
        if (lista != null){
            Toast.makeText(this,"Usuario guardado corectamente", Toast.LENGTH_SHORT).show()

            Log.i("añadir","${txtNombreAAgregar} ---> ${txtdescAAgregar}")
        }else{
            Toast.makeText(this,"Usuario no Ingresado", Toast.LENGTH_SHORT).show()

        }
    }

}