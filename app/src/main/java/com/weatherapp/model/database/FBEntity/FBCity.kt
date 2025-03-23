package com.weatherapp.model.database.FBEntity

import com.google.android.gms.maps.model.LatLng
import com.weatherapp.model.entity.City

class FBCity {
    var name : String? = null
    var lat : Double? = null
    var lng : Double? = null
    fun toCity(): City {
        val latlng = if (lat!=null&&lng!=null) LatLng(lat!!, lng!!) else null
        return City(name!!, weather = null, location = latlng)
    }
}