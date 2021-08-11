package com.example.myapplication_deber_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class notificacionesAc : AppCompatActivity() {
    val elementosNotificaciones = listOf<notificaciones>(notificaciones("https://www.marketingguerrilla.es/wp-content/uploads/2019/10/Wish-Home1.png",
        "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace una hora"),
        notificaciones("https://cr00.epimg.net/radio/imagenes/2019/08/28/tendencias/1566960714_798813_1566962221_noticia_normal_recorte1.jpg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace una hora"),
        notificaciones("https://marketing4ecommerce.net/wp-content/uploads/2017/02/comprar-en-wish_phixr.jpg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace 3 dias"),
        notificaciones("https://marketing4ecommerce.net/wp-content/uploads/2019/08/compra-en-wisj.jpg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace 6 horas"),
        notificaciones("https://www.enjoysabadell.com/wp-content/uploads/2019/11/wish.jpg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace 21 horas"),
        notificaciones("https://imagenes.lainformacion.com/files/image_606_387/uploads/imagenes/2019/03/13/5c893479ce3a1.jpeg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace un dia"),
        notificaciones("https://puertaapuerta.com.ar/wp-content/uploads/2018/11/por-que-wish-barato.png",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace un dia"),
        notificaciones("https://www.goloyos.com/wp-content/uploads/2019/01/image-1024x473.png",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace 2 horas"),
        notificaciones("https://www.goloyos.com/wp-content/uploads/2019/01/20190109_204014-768x576.jpg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace 23 horas"),
        notificaciones("https://cdn.1001cuponesdedescuento.cl/cl/Ea3CPvYKyymdwubjq2ku.png",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace un dia"),
        notificaciones("https://www.ecestaticos.com/image/clipping/557/418/42951aea5905183121347f92e5824f33/wish-el-amazon-chino-que-arrasa-en-espana-para-ahorrar-dinero-en-tus-compras.jpg",
            "¡Tenemos una coleccion repleta de articulos con chifon para ti! Compra ahora en wish para ofertas increibles.", "hace 3 dias"),)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)
        iniciarRecycler()
    }
    fun iniciarRecycler() {
        val recyclerViewWish = findViewById<RecyclerView>(R.id.id_recyclernotificaciones)
        recyclerViewWish.layoutManager = LinearLayoutManager(this)
        val adapter = AdaptadorDeNotificaciones(elementosNotificaciones)
        recyclerViewWish.adapter = adapter
    }
}