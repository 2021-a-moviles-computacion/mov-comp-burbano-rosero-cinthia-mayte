import java.io.*
import javax.swing.JOptionPane
class ArchivoDoctor {
    fun escribir(_Doctor: ArrayList<_Doctor>) {
        var salida: PrintWriter? = null
        try {
            // escritura de datos
            salida = PrintWriter(BufferedWriter(FileWriter("Doctor.txt")))

            for (i in _Doctor.indices) {
                salida.write(
                    "ID DOCTOR: "+_Doctor[i].idDoctor+ " "
                        .toString() + "NOMBRE: " + _Doctor[i].nombreDoc+ " " + "FECHA DE NACIMIENTO: " + _Doctor[i].fechaNac+ " " +
                            "DISCAPACIDAD: " + _Doctor[i].discapacidad+ " "+"ESPECIALIDAD"+ _Doctor[i].especialidad+ " "+"TALLA: " + _Doctor[i].talla + " "+
                            "PESO: " + _Doctor[i].peso+ "\n"
                )
            }
            salida.close()
        } catch (ex: IOException) {
            JOptionPane.showMessageDialog(null, "Datos no Compatibles")
        } finally {
            salida!!.close()
        }

    }

    fun leer(Paciente: ArrayList<_Doctor>): ArrayList<_Doctor>? {
        val arrayPaciente: ArrayList<_Doctor> = ArrayList<_Doctor>()
        try {


            //lectura de datos
            val entrada = BufferedReader(FileReader("Doctor.txt"))
            var s: String
            var s2 = String()
            var idPaciente: Int
            var nombrePaciente: String
            var fechaNac: String
            var discapacidad: Boolean
            var especialidad:String
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
                especialidad= informacion[4]
                talla = informacion[5].toFloat()
                peso = informacion[6].toFloat()
                val objDoctor = _Doctor(idPaciente, nombrePaciente, fechaNac,discapacidad,especialidad,talla,peso)
                arrayPaciente.add(objDoctor)
            }
            println("Paciente en Base de Datos:\n$s2")
            entrada.close()
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null, "El Paciente no se encuentra en la Base")
        }
        return arrayPaciente
    }

}