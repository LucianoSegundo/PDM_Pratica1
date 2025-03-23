package com.weatherapp.model.entity

import com.google.android.gms.maps.model.LatLng
import com.weatherapp.model.database.FBEntity.FBCity

data class City(
    val name: String,
    var location: LatLng? = null,
    var weather: Weather? = null,
    var forecast: List<Forecast>? = null,
    val isMonitored: Boolean = false,
    val salt: Long? = null


    ){
    fun toFBCity() : FBCity {
        val fbCity = FBCity()
        fbCity.name = this.name
        fbCity.lat = this.location?.latitude ?: 0.0
        fbCity.lng = this.location?.longitude ?: 0.0
        fbCity.monitored = this.isMonitored
        return fbCity
    }

}
