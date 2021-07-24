package com.example.myexamen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.Date

class SqliteHelperExamen (contexto: Context?): SQLiteOpenHelper(
    contexto,
    "moviles.db",
    null,
    3

) {
    override fun onCreate(db: SQLiteDatabase?) {
        //Tabla doctor
        val scripCrearTablaDoctor="""
            CREATE TABLE DOCTOR(idDoctor INTEGER PRIMARY KEY AUTOINCREMENT, 
            cedula VARCHAR(10),
            nombre VARCHAR(50),
            edad INTEGER,
            telefono VARCHAR(10),
            correo VARCHAR(50),
            especialidad VARCHAR(50)
            );
        """.trimIndent()
        Log.i("bdd", "Creacion tabla doctor")
        db?.execSQL(scripCrearTablaDoctor)
        //tabla Paciente
        val scripCrearTablaPaciente="""
            CREATE TABLE PACIENTE(idPaciente INTEGER PRIMARY KEY AUTOINCREMENT, 
            idDoctor INTEGER, 
            cedulaPaciente VARCHAR(10),
            nombrePaciente VARCHAR(50),
            edadPaciente INTEGER,
            telefonoPaciente VARCHAR(10),
            correoPaciente VARCHAR(50),
            FOREIGN KEY(idDoctor) REFERENCES DOCTOR(idDoctor)
            );
        """.trimIndent()
        Log.i("bdd", "Creacion tabla paciente")
        db?.execSQL(scripCrearTablaPaciente)

    }
    //Funcion agregar Doctor
    fun agregarDoctor(cedula:String,nombre:String,edad:Int,telefono:String,correo:String,especialidad:String):Boolean{
        val conexionEscrituraDoctor= writableDatabase
        val valoresAGuardar= ContentValues()
        valoresAGuardar.put("cedula",cedula)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("edad",edad)
        valoresAGuardar.put("telefono",telefono)
        valoresAGuardar.put("correo",correo)
        valoresAGuardar.put("especialidad",especialidad)
        val resultadoEscrituraDoctor:Long = conexionEscrituraDoctor.insert("DOCTOR",null,valoresAGuardar)
        conexionEscrituraDoctor.close()
        return if(resultadoEscrituraDoctor.toInt()==-1)false else true
    }
    //Funcion agregar Paciente
    fun agregarPaciente(correoPaciente:String,telefonoPaciente:String,edadPaciente:Int,cedulaPaciente:String,idDoctor:Int,nombrePaciente:String):Boolean{
        val conexionEscrituraPaciente= writableDatabase
        val datosPaciente= ContentValues()
        datosPaciente.put("idDoctor",idDoctor)
        datosPaciente.put("cedulaPaciente",cedulaPaciente)
        datosPaciente.put("nombrePaciente", nombrePaciente)
        datosPaciente.put("edadPaciente",edadPaciente)
        datosPaciente.put("telefonoPaciente",telefonoPaciente)
        datosPaciente.put("correoPaciente",correoPaciente)

        val resultadoEscrituraPaciente: Long = conexionEscrituraPaciente.insert("PACIENTE",null,datosPaciente)
        conexionEscrituraPaciente.close()
        return if(resultadoEscrituraPaciente.toInt()==-1)false else true
    }
    //funcion eliminar Doctor
    fun eliminarDoctor(idDoctor:Int):Boolean{
        val conexionEscrituraDoctor=writableDatabase
        val resultadoEliminarDoctor= conexionEscrituraDoctor.delete("DOCTOR", "idDoctor=?",
        arrayOf(idDoctor.toString()))
        conexionEscrituraDoctor.close()

        return if (resultadoEliminarDoctor.toInt()==-1) false else true
    }
    //funcion eliminar Paciente
    fun eliminarPaciente(idPaciente:Int):Boolean{
        val conexionEscrituraPaciente=writableDatabase
        val resultadoEliminarPaciente= conexionEscrituraPaciente.delete("PACIENTE", "idPaciente=?",
            arrayOf(idPaciente.toString()))
        conexionEscrituraPaciente.close()
        return if (resultadoEliminarPaciente.toInt()==-1) false else true
    }
    //funcion actualizar Doctor
    fun actualizarDoctor(nombre:String,edad:Int,telefono: String,correo: String,especialidad: String,idActualizar:Int):Boolean{
        val conexionEscritura=writableDatabase
        val valorAActualizarDoctor=ContentValues()
        valorAActualizarDoctor.put("nombre",nombre)
        valorAActualizarDoctor.put("edad",edad)
        valorAActualizarDoctor.put("telefono",telefono)
        valorAActualizarDoctor.put("correo",correo)
        valorAActualizarDoctor.put("especialidad",especialidad)
        val resultadoActualizarDoctor= conexionEscritura.update(
            "DOCTOR",valorAActualizarDoctor,"idDoctor=?",
            arrayOf(idActualizar.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizarDoctor ==-1) false else true
    }

    //funcion actualizar Paciente
    fun actualizarPaciente(idiDoctor:Int,nombrePac:String,edadPac:Int,telefonoPac: String,correoPac: String,idPacActualizar:Int):Boolean{
        val conexionEscritura=writableDatabase
        val valorAActualizarPaciente=ContentValues()
        valorAActualizarPaciente.put("idDoctor",idiDoctor)
        valorAActualizarPaciente.put("nombrePaciente",nombrePac)
        valorAActualizarPaciente.put("edadPaciente",edadPac)
        valorAActualizarPaciente.put("telefonoPaciente",telefonoPac)
        valorAActualizarPaciente.put("correoPaciente",correoPac)
        val resultadoActualizarPaciente= conexionEscritura.update(
            "PACIENTE",valorAActualizarPaciente,"idPaciente=?",
            arrayOf(idPacActualizar.toString())
        )
        conexionEscritura.close()
        return if(resultadoActualizarPaciente.toInt()==-1)false else true
    }
    //funcion mostrar doctor
    fun mostrarDoctor(): ArrayList<doctorBDD>{

        val consultaDoctor = "SELECT * FROM DOCTOR"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(consultaDoctor, null)
        val existeDoctor = resultaConsultaLectura.moveToFirst()
        var arregloDoctor = arrayListOf<doctorBDD>()

        if(existeDoctor){
            do{
                val id = resultaConsultaLectura.getInt(0)
                if(id !=null){
                    arregloDoctor.add(doctorBDD(id,resultaConsultaLectura.getString(1),
                    resultaConsultaLectura.getString(2),
                    resultaConsultaLectura.getInt(3),
                    resultaConsultaLectura.getString(4),
                    resultaConsultaLectura.getString(5),
                    resultaConsultaLectura.getString(6),))
                }

            }while(resultaConsultaLectura.moveToNext())
            Log.i("bdd", "Se devolvieron todos los doctores")
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloDoctor
    }
    //funcion mostrar Paciente
    fun mostrarPaciente(): ArrayList<PacienteBDD>{

        val consultaPaciente = "SELECT * FROM PACIENTE"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(consultaPaciente, null)
        val existePaciente = resultaConsultaLectura.moveToFirst()
        var arregloPaciente = arrayListOf<PacienteBDD>()

        if(existePaciente){
            do{
                val id = resultaConsultaLectura.getInt(0)
                if(id !=null){
                    arregloPaciente.add(
                        PacienteBDD(id,resultaConsultaLectura.getInt(1),
                        resultaConsultaLectura.getString(2),
                        resultaConsultaLectura.getString(3),
                        resultaConsultaLectura.getInt(4),
                        resultaConsultaLectura.getString(5),
                        resultaConsultaLectura.getString(6),)
                    )
                }

            }while(resultaConsultaLectura.moveToNext())
            Log.i("bdd", "Se devolvieron todos los Pacientes")
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloPaciente
    }
    //Funcion para buscar paciente por doctor
    fun consultarPacientePorDoctor(idDoc:Int):ArrayList<PacienteBDD>{
        val scripConsulta ="SELECT * FROM PACIENTE WHERE idDoctor = ${idDoc}"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(scripConsulta, null)
        val existePaciente = resultaConsultaLectura.moveToFirst()
        var arregloPaciente = arrayListOf<PacienteBDD>()

        if(existePaciente){
            do{
                val id = resultaConsultaLectura.getInt(0)
                if(id !=null){
                    arregloPaciente.add(
                        PacienteBDD(id,idDoc,
                            resultaConsultaLectura.getString(2),
                            resultaConsultaLectura.getString(3),
                            resultaConsultaLectura.getInt(4),
                            resultaConsultaLectura.getString(5),
                            resultaConsultaLectura.getString(6),)
                    )
                }

            }while(resultaConsultaLectura.moveToNext())
            Log.i("bdd", "Se devolvieron todos los Pacientes")
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloPaciente
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}