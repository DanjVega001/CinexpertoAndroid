package com.cinexperto.app.features.trivia.data.models

import com.google.gson.annotations.SerializedName

data class PublishedTrivia(
    val id:Int,
    @SerializedName("trivia_id") val triviaID:Int,
    val question:String
)