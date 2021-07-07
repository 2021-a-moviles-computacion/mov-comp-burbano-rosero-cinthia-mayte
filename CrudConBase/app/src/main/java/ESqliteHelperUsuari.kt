import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log

ESqliteHelperUsuari(contexto: Context?):SQLiteOpenHelper(contexto,"moviles",null,1) {
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
        val valorsAGuardar = ContentValues()
        valorsAGuardar.put("nombre",nombre)
        valorsAGuardar.put("descripcion",descriccion)
        val resultadoEscritura:Long =conexionEscritura.insert(
            "USUARIO",null,valorsAGuardar
        )
        conexionEscritura.close()
        return if(resultadoEscritura.toInt()==-1) false else true
    }
    fun consultarUsuarioPorId(id:Int): EUsuarioBDD {
        val scriptConsultarUsuario ="SELECT *FROM USUARIO WHERE ID=$id"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0," ","")
        if(existeUsuario) {
            do {
                val id =resultaConsultaLectura.getInt(0)
                val nombre = resultaConsultaLectura.getString(1)
                val descripcion = resultaConsultaLectura.getString(2)
                if(id!=null){
                    usuarioEncontrado.id=id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion=descripcion
                    //arregloUsuario.add(usuarioEncontrado)
                }
            }while(resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
    fun MostartDatos(): EUsuarioBDD {
        val scriptConsultarUsuario ="SELECT *FROM USUARIO"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0," ","")
        if(existeUsuario) {
            do {
                val id =resultaConsultaLectura.getInt(0)
                val nombre = resultaConsultaLectura.getString(1)
                val descripcion = resultaConsultaLectura.getString(2)
                if(id!=null){
                    usuarioEncontrado.id=id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion=descripcion
                    //arregloUsuario.add(usuarioEncontrado)
                }
            }while(resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
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
    fun actualizarUsuarioFormulario(
        nombre:String, descriccion: String, idActualizar:IncompatibleClassChangeError):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar= ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion",descriccion)
        val resultadoActualizacion = conexionEscritura.update(
            "USUARIO",
            valoresAActualizar,
            "id=?",
            arrayOf(
                idActualizar.toString()
            )
        )
        conexionEscritura.close()
        return if(resultadoActualizacion==-1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}