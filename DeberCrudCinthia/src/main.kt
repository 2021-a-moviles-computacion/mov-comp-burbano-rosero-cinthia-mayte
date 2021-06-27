fun main(){
    println("Presiona \n R para leer." +
            "\n C para crear.\n U para actualizar.\n D para eliminar")
    val menu = readLine()

    //Crear archivo
    

    var Datos = arrayOfNulls<Paciente>(2)
    Datos[0]=Paciente(1,"Jadiel ","14042008",false,1.50F, 62.5F )
    Datos[1]=Doctor(1,"Fernando ","Obstetra","19950916",false,
        1.70F, 70.00f, 1 )

   /*for (i in Datos.indices){
        println(Datos[i]!!.MostrarDatos())
    }*/

    when (menu){
        ("R") ->{
            for (i in Datos.indices){
        println(Datos[i]!!.MostrarDatos())
        }

        }
        ("r") ->{
            for (i in Datos.indices){
                println(Datos[i]!!.MostrarDatos())
            }

        }
        ("C") ->{

        }
        ("U") ->{
        }
        ("D") ->{
           }
    }
}
