package com.example.moviles_computacion_2021_b

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(BEntrenador("Kari", "a@a.com"))
            arregloBEntrenador
                .add(BEntrenador("Alisson", "d@b.com"),)
            arregloBEntrenador
                .add(BEntrenador("Andrea", "cf@c.com"))

        }
    }
}