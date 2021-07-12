class Paciente (var idPaciente:Int, var nombrePaciente:String, var fechaNacim:String,var discapacidad:Boolean
,var talla:Float, var peso:Float){
    override fun toString(): String {
        return "IDPACIENTE$idPaciente, NOMBRE_PACIENTE=$nombrePaciente, " +
                "FECHA_NACIMIENTO=$fechaNacim, DISCAPACIDAD=$discapacidad, TALLA=$talla" +
                "PESO=$peso"
    }
}