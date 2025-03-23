package com.weatherapp.model.entity

import com.weatherapp.model.database.FBEntity.FBUser

data class User(val name: String, val email: String){
    fun toFBUser() : FBUser {
        val fbUser = FBUser()
        fbUser.name = this.name
        fbUser.email = this.email
        return fbUser
    }

}
