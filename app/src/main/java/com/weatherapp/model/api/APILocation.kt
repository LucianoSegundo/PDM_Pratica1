package com.weatherapp.model.api

data class APILocation (
    var id : String? = null,
    var name : String? = null,
    var region : String? = null,
    var country : String? = null,
    var lat : Double? = null,
    var lon: Double? = null,
    var url : String? = null
)

data class APICondition (
    var text : String? = null,
    var icon : String? = null
)
data class APIWeather (
    var last_updated: String? = null,
    var temp_c : Double? = 0.0,
    var maxtemp_c: Double? = 0.0,
    var mintemp_c: Double? = 0.0,
    var condition : APICondition? = null
)
data class APICurrentWeather (
    var location : APILocation? = null,
    var current : APIWeather? = null
)
