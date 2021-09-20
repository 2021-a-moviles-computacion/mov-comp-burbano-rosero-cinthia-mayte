package com.example.examen2b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val listaDoctor: ArrayList<Doctor>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    public class MyViewHolder( itemView:View): RecyclerView.ViewHolder(itemView){
        val idDoctor:TextView = itemView.findViewById(R.id.id_tv_doctor)
        val cedula:TextView =itemView.findViewById(R.id.id_tv_cedula)
        val nombre:TextView =itemView.findViewById(R.id.id_tv_nombre)
        val edad:TextView =itemView.findViewById(R.id.id_tv_edad)
        val telefono:TextView =itemView.findViewById(R.id.id_tv_telefono)
        val correo:TextView =itemView.findViewById(R.id.id_tv_correo)
        val especialidad:TextView  =itemView.findViewById(R.id.id_tv_especialidad)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.vista_doctor,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val doctor :Doctor = listaDoctor[position]
        holder.idDoctor.text=doctor.idDoctor
        holder.cedula.text = doctor.cedulaDoc
        holder.nombre.text = doctor.nombre
        holder.edad.text = doctor.edadDoc.toString()
        holder.telefono.text = doctor.telefonoDoc
        holder.correo.text = doctor.correoDoc
        holder.especialidad.text = doctor.especialidad

    }

    override fun getItemCount(): Int {
       return listaDoctor.size
    }


}