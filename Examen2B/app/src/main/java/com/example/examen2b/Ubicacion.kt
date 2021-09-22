package com.example.examen2b

import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Ubicacion : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion)

        solicitarPermisos()
        val paciente = intent.getParcelableExtra<Paciente>("Paciente")
        var latitud = paciente!!.latitud.toString().toDouble()
        var longitUd = paciente.longitud.toString().toDouble()

        val fmapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        fmapa.getMapAsync { googleMap ->
            if(googleMap != null){
                val ubicacion = LatLng(latitud, longitUd)
                mapa = googleMap
                establecerConfiguracionMapa()
                anadirMarcador(ubicacion, "UBICACION")
                moverCamaraConZoom(ubicacion, 17f)
            }
        }


    }


    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos= permisosFineLocation== PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            permisos= true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(//arreglo de permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 // Codigo de peticion de permisoa
            )
        }

    }
    fun escucharListeners(){
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
        }
        mapa.setOnPolylineClickListener  {
            Log.i("mapa", "setOnPolylineClickListener ${it}")
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener ")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener ")
        }
    }
    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }

            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }
    fun anadirMarcador(latLng: LatLng, title: String){
        mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom:Float= 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng,zoom)
        )
    }
}
