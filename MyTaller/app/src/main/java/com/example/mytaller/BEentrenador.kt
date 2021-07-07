package com.example.mytaller

import android.os.Parcel
import android.os.Parcelable

class BEentrenador (val nombre: String?, val descripcion: String?, val liga:DLiga?):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(DLiga::class.java.classLoader)
    ) {
    }

    override  fun toString():String{
        return "$nombre - $descripcion"
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(nombre)
        parcel?.writeString(descripcion)
        parcel?.writeParcelable(liga,flags)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEentrenador> {
        override fun createFromParcel(parcel: Parcel): BEentrenador {
            return BEentrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEentrenador?> {
            return arrayOfNulls(size)
        }
    }

}