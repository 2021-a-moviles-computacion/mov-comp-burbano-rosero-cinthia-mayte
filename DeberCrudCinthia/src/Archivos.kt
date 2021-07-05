import java.io.*
import javax.swing.JOptionPane


class Archivos {

       
    fun escribir(Paciente: ArrayList<Paciente>) {
        var salida: PrintWriter? = null
        try {
            // escritura de datos
            salida = PrintWriter(BufferedWriter(FileWriter("DeberCrud.txt")))

            for (i in Paciente.indices) {
                salida.write(
                    "ID PACIENTE: "+Paciente[i].idPaciente+ " "
                        .toString() + "NOMBRE: " + Paciente[i].nombrePaciente+ " " + "FECHA DE NACIMIENTO: " + Paciente[i].fechaNacim+ " " +
                            "DISCAPACIDAD: " + Paciente[i].discapacidad+ " "+"TALLA: " + Paciente[i].talla + " "+
                            "PESO: " + Paciente[i].peso+ "\n"
                )
            }
            salida.close()
        } catch (ex: IOException) {
            JOptionPane.showMessageDialog(null, "Datos no Compatibles")
        } finally {
            salida!!.close()
        }

    }

    fun leer(Paciente: ArrayList<Paciente>): ArrayList<Paciente>? {
        val arrayPaciente: ArrayList<Paciente> = ArrayList<Paciente>()
        try {


            //lectura de datos
            val entrada = BufferedReader(FileReader("DeberCrud.txt"))
            var s: String
            var s2 = String()
            var idPaciente: Int
            var nombrePaciente: String
            var fechaNac: String
            var discapacidad: Boolean
            var peso: Float
            var talla: Float
            while (entrada.readLine().also { s = it } != null) {
                s2 += """
                $s
                
                """.trimIndent()
                val informacion = s.split("-".toRegex()).toTypedArray()
                idPaciente = informacion[0].toInt()
                nombrePaciente = informacion[1]
                fechaNac = informacion[2]
                discapacidad= informacion[3].toBoolean()
                talla = informacion[4].toFloat()
                peso = informacion[5].toFloat()
                val objPaciente = Paciente(idPaciente, nombrePaciente, fechaNac,discapacidad,talla,peso)
                arrayPaciente.add(objPaciente)
            }
            println("Paciente en Base de Datos:\n$s2")
            entrada.close()
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null, "El Paciente no se encuentra en la Base")
        }
        return arrayPaciente
    }
    fun actualizar(Paciente: ArrayList<Paciente>) {
        var salida: PrintWriter? = null
        try {
            // escritura de datos
            salida = PrintWriter(BufferedWriter(FileWriter("DeberCrud.txt")))

            for (i in Paciente.indices) {
                salida.write(
                    "ID PACIENTE: "+Paciente[i]+ " "
                        .toString() + "NOMBRE: " + Paciente[i].nombrePaciente+ " " + "FECHA DE NACIMIENTO: " + Paciente[i].fechaNacim+ " " +
                            "DISCAPACIDAD: " + Paciente[i].discapacidad+ " "+"TALLA: " + Paciente[i].talla + " "+
                            "PESO: " + Paciente[i].peso+ "\n"
                )
            }
            salida.close()
        } catch (ex: IOException) {
            JOptionPane.showMessageDialog(null, "Datos no Compatibles")
        } finally {
            salida!!.close()
        }

    }
}