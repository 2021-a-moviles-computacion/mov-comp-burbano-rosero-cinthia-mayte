package com.example.myapplication_deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adaptador(val listaDeElementos:List<ListaElementos>):RecyclerView.Adapter<adaptador.listaHolder>(){

    inner class listaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val precio1TextView:TextView
        val precio2TextView:TextView
        var descripcion1TextView:TextView
        val descripcion2TextView:TextView
        val imagen1:ImageView
        val imagen2:ImageView


        init{
            precio1TextView= view.findViewById(R.id.id_precio1)
            precio2TextView= view.findViewById(R.id.id_precio2)
            descripcion1TextView = view.findViewById(R.id.id_descripcion1)
            descripcion2TextView= view.findViewById(R.id.id_descripcion2)
            imagen1 = view.findViewById(R.id.id_imagen1)
            imagen2 =  view.findViewById(R.id.id_imagen2)

        }

        fun render(listaElementos: ListaElementos) {
            //view.setOnClickListener{}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listaHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return listaHolder(layoutInflater.inflate(R.layout.activity_elementos,parent,false))

    }

    override fun onBindViewHolder(holder: listaHolder, position: Int) {
        val element= listaDeElementos[position]


        holder.precio1TextView.text =element.precio
        holder.precio2TextView.text =element.precio2
        holder.descripcion1TextView.text= element.descripcion
        holder.descripcion2TextView.text = element.descripcion2
        Picasso.get().load(element.image).into(holder.imagen1)
        Picasso.get().load(element.image2).into(holder.imagen2)
        holder.render(element);

    }

    override fun getItemCount(): Int {
       return listaDeElementos.size
    }



}