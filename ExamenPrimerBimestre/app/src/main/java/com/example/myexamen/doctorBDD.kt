package com.example.myexamen

import android.os.Parcel
import android.os.Parcelable

class doctorBDD (var idDoctor:Int, var cedulaDoc:String?, var nombre:String?, var edadDoc:Int,
                 var telefonoDoc:String?, var correoDoc: String?, var especialidad:String?
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idDoctor)
        parcel.writeString(cedulaDoc)
        parcel.writeString(nombre)
        parcel.writeInt(edadDoc)
        parcel.writeString(telefonoDoc)
        parcel.writeString(correoDoc)
        parcel.writeString(especialidad)
    }
    override fun toString(): String {
        return "ID DOCTOR:  $idDoctor \n" +
                "CEDULA:     $cedulaDoc \n" +
                "NOMBRE:  $nombre \n" +
                "EDAD:       $edadDoc \n" +
                "TELEFONO:  $telefonoDoc \n" +
                "CORREO:   $correoDoc \n " +
                "ESPECIALIDAD: $especialidad"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<doctorBDD> {
        override fun createFromParcel(parcel: Parcel): doctorBDD {
            return doctorBDD(parcel)
        }

        override fun newArray(size: Int): Array<doctorBDD?> {
            return arrayOfNulls(size)
        }
    }
}