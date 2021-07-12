import java.util.*

  class _Doctor(
     var idDoctor: Int, var nombreDoc: String, var fechaNac: String,
     var discapacidad: Boolean,var especialidad:String, var talla: Float, var peso: Float)
{

   override fun toString(): String {
        return "IDDOCTOR$idDoctor, NOMBREDOCTOR=$nombreDoc, " +
                "FECHA_NACIMIENTO=$fechaNac, DISCAPACIDAD=$discapacidad,ESPECIALIDAD =$especialidad, TALLA=$talla" +
                "PESO=$peso"
    }
}