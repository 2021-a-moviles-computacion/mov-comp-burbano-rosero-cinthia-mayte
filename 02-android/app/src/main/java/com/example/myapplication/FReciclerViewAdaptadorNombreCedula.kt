package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FReciclerViewAdaptadorNombreCedula ( private val contexto: Class<*>,
    private  val listaEntrenador: List<BEntrenador>,
    private val recycledView:RecyclerView
    ): RecyclerView.Adapter<FReciclerViewAdaptadorNombreCedula.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionesBoton: Button
        var numeroLikes = 0

        init{
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView =view.findViewById(R.id.tv_cedula)
            likesTextView=view.findViewById(R.id.tv_likes)
            accionesBoton=view.findViewById(R.id.btn_dar_like)
            accionesBoton.setOnClickListener {
                this.anadirLike()
            }



        }
        fun anadirLike(){
           this.numeroLikes=this.numeroLikes+1
           likesTextView.text=this.numeroLikes.toString()
        }
    }

//setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView= LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recicler_view_vista, //Definimos la vista de nuestro recycler view
                parent,false
            )
         return MyViewHolder(itemView)
    }
//setear los datos de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     val entrenador= listaEntrenador[position]
        //se inicializan los datos
        holder.nombreTextView.text=entrenador.nombre
        holder.cedulaTextView.text=entrenador.descripcion
        holder.accionesBoton.text="Like ${entrenador.nombre}"
        holder.likesTextView.text="0"
    }

       override fun getItemCount(): Int {
        return listaEntrenador.size
    }


}
