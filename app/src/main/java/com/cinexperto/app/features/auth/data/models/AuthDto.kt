package com.cinexperto.app.features.auth.data.models

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("name") val username:String ?,
    val email:String,
    val password:String
)
