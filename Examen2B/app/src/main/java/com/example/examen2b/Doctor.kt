package com.example.examen2b

class Doctor (var idDoctor:String? =null, var cedulaDoc:String ?= null, var nombre:String ?= null, var edadDoc:Int?= null, var telefonoDoc:String?= null
, var correoDoc: String?= null, var especialidad:String?= null){
    override fun toString(): String {
        return "ID DOCTOR:  $idDoctor \n" +
                "CEDULA:     $cedulaDoc \n" +
                "NOMBRE:  $nombre \n" +
                "TELEFONO:  $telefonoDoc \n" +
                "CORREO:   $correoDoc \n " +
                "ESPECIALIDAD: $especialidad"
    }
}











