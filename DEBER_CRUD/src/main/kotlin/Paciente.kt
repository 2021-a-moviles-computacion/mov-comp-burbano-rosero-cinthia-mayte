import java.util.*

open class Paciente(
    val idPaciente:Int, val nombre: String, val fechaNac: String, val discapacidad:Boolean,
    val talla:Float, val peso: Float)
{
    open  fun MostrarDatos():String{

        return "NOMBRE=${nombre}, FECHA NACIMIENTO =${fechaNac}, " +
                "DISCAPACIDAD= ${discapacidad}, TALLA=$talla, PESO=$peso, idPACIENTE=$idPaciente"
    }
}