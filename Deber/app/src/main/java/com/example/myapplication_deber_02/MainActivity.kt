package com.example.myapplication_deber_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val elementosWish = listOf<ListaElementos>(
        ListaElementos("3 US$","+10.000 compraron esto","https://www.dhresource.com/0x0/f2/albu/g4/M00/9F/58/rBVaEFc_C0WAK4ISAAH0MZFCxFM171.jpg",
            "3,87 US$", "+5.000 compraron esto","https://i2.wp.com/blog.local.wish.com/wp-content/uploads/2020/03/smart-watch.png?resize=720%2C678&ssl=1"),
        ListaElementos("0,50 US$","+1.000 compraron esto","https://merchantfaq.wish.com/hc/article_attachments/360078714674/mceclip0.png",
        "3,78 US$", "+20.000 compraron esto","https://merchantfaq.wish.com/hc/article_attachments/360079848393/mceclip2.png"),
        ListaElementos("23 US$","Producto mas vendido","https://merchantfaq.wish.com/hc/article_attachments/360078251253/Screen_Shot_2020-06-30_at_4.12.36_PM.png",
            "1,93 US$", "Usuarios como tu compraron","https://www.tuexpertoapps.com/wp-content/uploads/2017/07/water-luz.jpg"),
        ListaElementos("4,90 US$","+50.000 compraron esto","https://d3ugyf2ht6aenh.cloudfront.net/stores/541/600/products/productos_wish-portapincel_21-901517cf773a81297d15869965047690-1024-1024.jpg",
            "1,80 US$", "+50.000 compraron esto","https://d3ugyf2ht6aenh.cloudfront.net/stores/541/600/products/porfa_img_5046-copia1-6c0fa3dc6729b9581516071871016264-1024-1024.jpg"),
        ListaElementos("13 US$","+1.000 compraron esto","https://d3ugyf2ht6aenh.cloudfront.net/stores/541/600/products/91-cbe9ef4f1e842cee3616261280925349-1024-1024.jpg",
            "1,10 US$", "+10.000 compraron esto","https://d3ugyf2ht6aenh.cloudfront.net/stores/541/600/products/11_221-d8caed4a6c02a3153d16261298404210-1024-1024.jpg"),
        ListaElementos("4 US$","+5.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-productos-wish-880x440.jpg",
            "3,56 US$", "+20.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-productos-wish-880x440.jpg"),
        ListaElementos("0,89 US$","Basado en tu compra","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-pt-br-x-produtos-bizarros-da-wish-dentes-280x280.png",
            "0,81 US$", "+50.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-pt-br-x-produtos-bizarros-da-wish-almofada-nicolas-cage-280x280.png"),
        ListaElementos("4 US$","+5.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-pt-br-x-produtos-bizarros-da-wish-pokemons-280x280.png",
            "2 US$", "+1.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-pt-br-lingua-falsa-280x280.png"),
        ListaElementos("1,70 US$","+20.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-pt-br-x-produtos-bizarros-da-wish-nicolas-cage-280x280.png",
            "2 US$", "+50.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2019/12/es-10-productos-de-wish-para-decir-wtf-pt-br-x-produtos-bizarros-da-wish-escova-de-banheiro-280x280.png"),
        ListaElementos("13 US$","+10.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2018/08/Vuelta-Al-Cole-Kawaii-15-adorables-arti%CC%81culos-de-papeleri%CC%81a-03-280x280.jpg",
            "2,84 US$", "+5.000 compraron esto","https://theshoppers.com/es/wp-content/uploads/sites/6/2018/08/Vuelta-Al-Cole-Kawaii-15-adorables-arti%CC%81culos-de-papeleri%CC%81a-13-280x280.jpg")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarRecycler()
    }
    fun iniciarRecycler(){
        val recyclerViewWish= findViewById<RecyclerView>(R.id.id_recyclerView)
            recyclerViewWish.layoutManager = LinearLayoutManager(this)
        val adapter = adaptador(elementosWish)
        recyclerViewWish.adapter=adapter
    }
}