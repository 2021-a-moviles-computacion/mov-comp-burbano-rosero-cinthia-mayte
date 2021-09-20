package com.example.examen2b

import android.os.Parcel
import android.os.Parcelable

class Paciente (
    var idPaciente: String?=null, var idDoctor: String?=null, var cedulaPaciente:String?=null, var nombrePaciente:String?=null,
    val edadPaciente:Int?=null, var telefonoPaciente:String?=null, var correoPaciente:String?=null){



    override fun toString(): String {
        return "ID PACIENTE: $idPaciente \n" +
                "ID DOCTOR:  $idDoctor \n" +
                "CEDULA:     $cedulaPaciente \n" +
                "NOMBRE:     $nombrePaciente \n" +
                "TELEFONO:   $telefonoPaciente \n" +
                "CORREO:     $correoPaciente \n "

    }

}