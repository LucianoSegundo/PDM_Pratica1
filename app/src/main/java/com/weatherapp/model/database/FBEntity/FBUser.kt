package com.weatherapp.model.database.FBEntity

import com.weatherapp.model.entity.User

class FBUser {
    var name : String ? = null
    var email : String? = null
    fun toUser() = User(name!!, email!!)
}