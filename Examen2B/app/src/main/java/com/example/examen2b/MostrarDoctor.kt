package com.example.examen2b

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_mostrar_doctor.*

class MostrarDoctor : AppCompatActivity() {
    companion object{
        var idDodtor =0
    }
    var PosisionItem =0
    var adaptador: ArrayAdapter<*>?=null

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_doctor)

        btn_agregar_doctorIn.setOnClickListener {
            val inten: Intent = Intent(this, RegistrarDoctor::class.java)
            startActivity(inten)
        }
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        PosisionItem = id
       // idDodtor = adaptador!!.getItem(PosisionItem)!!.idDoctor
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var aDoctor = adaptador!!.getItem(PosisionItem)

        return when(item?.itemId){


            //Eliminar
            R.id.id_item_eliminar -> {
               /* if(BasesDeDatos.TablaDoctor!=null){

                    AlertDialog.Builder(this).apply {
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar Doctor?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BasesDeDatos.TablaDoctor!!.eliminarDoctor(aDoctor!!.idDoctor)
                            adaptador?.remove(adaptador!!.getItem(PosisionItem));

                        }
                        setNegativeButton("No", null)
                    }.show()


                }*/
                return true
            }

            //Editar
            R.id.id_iten_editar-> {

                if (aDoctor != null) {
                 //intent   abrirActividadConParametros(ActualizarDoctor::class.java,aDoctor)
                }

                return true
            }
            //MOSTRAR PACIENTE
            R.id.id_itemverPaciente-> {

                if (aDoctor != null) {
                  //intent  abrirActividadConParametros(mostrarPacientePorDoctor::class.java,aDoctor)
                }

                return true }

            else -> super.onContextItemSelected(item)
        }
    }

}