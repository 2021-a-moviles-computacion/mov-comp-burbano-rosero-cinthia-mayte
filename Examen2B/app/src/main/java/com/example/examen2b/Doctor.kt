package com.example.examen2b

import android.os.Parcel
import android.os.Parcelable

class Doctor (var idDoctor:String? ="", var cedulaDoc:String ?= "", var nombre:String ?= "", var edadDoc:Int?= 0, var telefonoDoc:String?= ""
, var correoDoc: String?= "", var especialidad:String?= ""): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "ID DOCTOR:  $idDoctor \n" +
                "CEDULA:     $cedulaDoc \n" +
                "NOMBRE:  $nombre \n" +
                "EDAD:  $edadDoc \n" +
                "TELEFONO:  $telefonoDoc \n" +
                "CORREO:   $correoDoc \n " +
                "ESPECIALIDAD: $especialidad"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idDoctor)
        parcel.writeString(cedulaDoc)
        parcel.writeString(nombre)
        parcel.writeValue(edadDoc)
        parcel.writeString(telefonoDoc)
        parcel.writeString(correoDoc)
        parcel.writeString(especialidad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Doctor> {
        override fun createFromParcel(parcel: Parcel): Doctor {
            return Doctor(parcel)
        }

        override fun newArray(size: Int): Array<Doctor?> {
            return arrayOfNulls(size)
        }
    }
}











