package com.example.mytaller

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuari(contexto: Context?):SQLiteOpenHelper(contexto,"moviles",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scripCrearTablaUsuario=""" 
            CREATE TABLE USUARIO(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre VARCHAR(50),
            descripcion VARCHAR(50)
            )
            """.trimIndent()
        Log.i("bbd","Creando la tabla de usuario")
        db?.execSQL(scripCrearTablaUsuario)

    }
    fun crearUsuarioFormulario(nombre:String,descriccion: String):Boolean{
        val conexionEscritura =writableDatabase
        val valorsAGuardar =ContentValues()
        valorsAGuardar.put("nombre",nombre)
        valorsAGuardar.put("descripcion",descriccion)
        val resultadoEscritura:Long =conexionEscritura.insert(
            "USUARIO",null,valorsAGuardar
        )
        conexionEscritura.close()
        return if(resultadoEscritura.toInt()==-1) false else true
    }
    fun consultarUsuarioPorId(id: Int): EUsuarioBDD{
        //var
        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
        val usuarioEncontrado = EUsuarioBDD(0,"","")
        if (existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1 -> NOMBRE
                val descripcion = resultadoConsultaLectura.getString(2) //Columna indice 2 -> DESCRIPCION

                if(id!=null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    //arregloUsuario.add(usuarioEncontrado)
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }



    fun eliminarUsuarioFormulario(id:Int):Boolean{
        val conexionEscritura =writableDatabase
        val resultadoEliminacion = conexionEscritura.delete(
            "USUARIO", "ID=?",
            arrayOf(
                id.toString()
            )
        )
        conexionEscritura.close()
        return  if(resultadoEliminacion.toInt()==-1)false else true
    }
    fun actualizarUsuarioFormulario (nombre :String,
                                     descripcion:String,
                                     id :Int):Boolean {

        val conexionEscritura = writableDatabase
        var valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura.update(
            "USUARIO",
            valoresActualizar,
            "id=?",
            arrayOf(id.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }



}