package com.example.myapplication_deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdaptadorDeNotificaciones(val listaDeNotificaciones:List<notificaciones>): RecyclerView.Adapter<AdaptadorDeNotificaciones.listaHolderNotificaciones>() {

    inner class listaHolderNotificaciones(val view: View) : RecyclerView.ViewHolder(view) {
        //Notificaciones
        val imagenview: ImageView
        val descripcionTextview: TextView
        val frecuenciaTextview: TextView

        init {

            //notificaciones
            imagenview = view.findViewById(R.id.id_imagenNotificaciones)
            descripcionTextview = view.findViewById(R.id.id_tv_notificaciones)
            frecuenciaTextview = view.findViewById(R.id.tv_frecuencia)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listaHolderNotificaciones {
        val layoutInflater= LayoutInflater.from(parent.context)
        return listaHolderNotificaciones(layoutInflater.inflate(R.layout.notificaciones,parent,false))

    }

    override fun getItemCount(): Int {
        return listaDeNotificaciones.size
    }

    override fun onBindViewHolder(holder: listaHolderNotificaciones, position: Int) {
        val elementNotifi= listaDeNotificaciones[position]
        //notificaciones
        holder.descripcionTextview.text= elementNotifi.descripcionNotificacion
        holder.frecuenciaTextview.text=elementNotifi.frecuencia
        Picasso.get().load(elementNotifi.imagen).into(holder.imagenview)
    }


}