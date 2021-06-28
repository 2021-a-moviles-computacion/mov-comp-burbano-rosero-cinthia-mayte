package com.example.mytaller

class BBaseDatosMemoria {
    //Todos los metodos y variables estaticas que podemos tener dentro de una clase
        companion object{
            val arregloBEentrenador = arrayListOf<BEentrenador>()
            init {
                arregloBEentrenador.add(BEentrenador("Cinthia","c@c.com"))
                arregloBEentrenador.add(BEentrenador("Mayte","m@c.com"))
                arregloBEentrenador.add(BEentrenador("Alex","a@c.com"))

            }
        }

}