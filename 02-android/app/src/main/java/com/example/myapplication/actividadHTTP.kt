package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class actividadHTTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_http)
        metodoGet()
        metodoPost()
    }
    fun metodoGet(){
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString {req,res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon", "Error:$error")

                    }
                    is Result.Success ->{
                        val getString= result.get()
                        Log.i("http-klaxon","$getString")
                        //
                        val post = Klaxon().parse<IPostsHttp>(getString)
                        Log.i("http-klaxon", "Error:${post?.body}")
                    }
                }

            }

    }
    //METODO POST
    fun metodoPost(){
        val parametros: List<Pair<String,*>> = listOf(
            "title" to "Titulo Moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )
        "https://jsonplaceholder.typicode.com/posts".httpPost(parametros)
            .responseString{request, response, result ->
                when (result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i( "http-klaxon", "Error:${error}")

                    }
                    is Result.Success ->{
                        val postString= result.get()
                        Log.i("http-klaxon","$postString")
                        //

                    }
                }
            }
    }
}