package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        //crear lista vacia de entrenadores y liga vacia
        val listaEntrenador= arrayListOf<BEntrenador>()
        val ligaPokemon =DLiga("Kanto","Liga Kanto")
        listaEntrenador.add(BEntrenador("Cinthia","Burbano",ligaPokemon))
        listaEntrenador.add(BEntrenador("Jadiel","Ramirez",ligaPokemon))

        //buscar el recyclerView por el identificador
        val recyclerViewEntrenador = findViewById<RecyclerView>(R.id.rv_entrenadores)

        iniciarReciclerView(listaEntrenador,this, recyclerViewEntrenador)
    }
    fun iniciarReciclerView(lista: List<BEntrenador>,actividad:GRecyclerView,recyclerView:RecyclerView){
        val adaptador =FReciclerViewAdaptadorNombreCedula(actividad,lista,recyclerView)
        recyclerView.adapter=adaptador
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager= androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }
    fun aumentoTotalLikes(){
        totalLikes=totalLikes +1
        val textView =findViewById<TextView>(R.id.tv_total_likes)
        textView.text=totalLikes.toString()
    }
}