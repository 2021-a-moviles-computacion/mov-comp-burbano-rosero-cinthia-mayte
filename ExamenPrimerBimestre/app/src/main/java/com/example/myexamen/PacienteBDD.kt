package com.example.myexamen

import android.os.Parcel
import android.os.Parcelable

class PacienteBDD (var idPaciente: Int, var idDoctor:Int, var cedulaPaciente:String?, var nombrePaciente:String?,
                    var edadPaciente:Int, var telefonoPaciente:String?, var correoPaciente:String?)
    :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPaciente)
        parcel.writeInt(idDoctor)
        parcel.writeString(cedulaPaciente)
        parcel.writeString(nombrePaciente)
        parcel.writeInt(edadPaciente)
        parcel.writeString(telefonoPaciente)
        parcel.writeString(correoPaciente)
    }
    override fun toString(): String {
        return "ID PACIENTE: $idPaciente \n" +
                "ID DOCTOR:  $idDoctor \n" +
                "CEDULA:     $cedulaPaciente \n" +
                "NOMBRE:     $nombrePaciente \n" +
                "EDAD:       $edadPaciente \n" +
                "TELEFONO:   $telefonoPaciente \n" +
                "CORREO:     $correoPaciente \n "

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PacienteBDD> {
        override fun createFromParcel(parcel: Parcel): PacienteBDD {
            return PacienteBDD(parcel)
        }

        override fun newArray(size: Int): Array<PacienteBDD?> {
            return arrayOfNulls(size)
        }
    }

}