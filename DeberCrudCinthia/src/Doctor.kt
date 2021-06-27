import java.util.*

class Doctor(
    val idDoctor: Int, nombre:String, val especialidad:String, fechaNac: String,
    discapacidad:Boolean, talla:Float, peso:Float, idPaciente:Int)
    :Paciente(idPaciente,nombre, fechaNac, discapacidad, talla, peso)
{
    override fun MostrarDatos():String{

        return "idDOCTOR=${idDoctor},NOMBRE=${nombre}, ESPECIALIDAD=${especialidad}, FECHA NACIMIENTO =${fechaNac}, " +
                "DISCAPACIDAD= ${discapacidad}, TALLA=$talla, PESO=$peso, idPACIENTE=$idPaciente"
    }
}