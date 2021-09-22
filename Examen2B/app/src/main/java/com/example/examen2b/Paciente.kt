package com.example.examen2b

import android.os.Parcel
import android.os.Parcelable

class Paciente (
    var idPaciente: String?="", var idDoctor: String?="null", var cedulaPaciente:String?="null", var nombrePaciente:String?="",
    var edadPaciente:Int?=0, var telefonoPaciente:String?="null", var correoPaciente:String?=
        "",var latitud: Double? = 0.0,
    var longitud: Double? = 0.0,) : Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun toString(): String {
        return "ID PACIENTE: $idPaciente \n" +
                "ID DOCTOR:  $idDoctor \n" +
                "CEDULA:     $cedulaPaciente \n" +
                "NOMBRE:     $nombrePaciente \n" +
                "EDAD:     $edadPaciente \n" +
                "TELEFONO:   $telefonoPaciente \n" +
                "CORREO:     $correoPaciente \n "+
                "LONGITUD:     $longitud \n "+
                "LATITUD:     $latitud \n "

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPaciente)
        parcel.writeString(idDoctor)
        parcel.writeString(cedulaPaciente)
        parcel.writeString(nombrePaciente)
        parcel.writeValue(edadPaciente)
        parcel.writeString(telefonoPaciente)
        parcel.writeString(correoPaciente)
        parcel.writeValue(latitud)
        parcel.writeValue(longitud)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }

}