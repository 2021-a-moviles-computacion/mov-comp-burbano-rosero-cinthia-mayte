package com.example.examen2b

import android.os.Parcel
import android.os.Parcelable

abstract class Doctor constructor(var idDoctor:String,var cedulaDoc:String,var nombre:String, var edadDoc:Int,var telefonoDoc:String
, var correoDoc: String,var especialidad:String) {


    init {
        this.idDoctor= idDoctor
        this.cedulaDoc= cedulaDoc
        this.nombre=nombre
        this.edadDoc = edadDoc
        this.telefonoDoc= telefonoDoc
        this.correoDoc= correoDoc
        this.especialidad= especialidad
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
}







