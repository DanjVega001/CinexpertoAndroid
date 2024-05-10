package com.cinexperto.app.features.auth.data.models

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("access_token") val accessToken:String
)