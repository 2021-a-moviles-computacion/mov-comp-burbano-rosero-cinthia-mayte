package com.example.mytaller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivityClase10 : AppCompatActivity() {
    var numero = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_clase10)
        //Logueador
        Log.i("ciclo-vida","onCreate")
        val textViewCicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewCicloVida.text=numero.toString()
        val buttonMainActivityClase10= findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )
        buttonMainActivityClase10.setOnClickListener { aumentarNumero() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
       // super.onSaveInstanceState(outState, outPersistentState)
        outState.run {
            //CUALQUIER PRIMITIVO
            putInt("NumeroGuardado",numero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado= savedInstanceState.getInt("numeroGuardado")
        if(numeroRecuperado != null){
            numero= numeroRecuperado
            val textViewCicloVida = findViewById<TextView>(
                R.id.txv_ciclo_vida)
            textViewCicloVida.text= numero.toString()
        }
    }

    fun aumentarNumero(){
        numero = numero +1
        val textViewMainActivityClase10 =findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewMainActivityClase10.text= numero.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")

    }
}