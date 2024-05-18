package com.cinexperto.app.features.trivia.data.models

import com.google.gson.annotations.SerializedName

data class GetTriviasDto(
    @SerializedName("trivia_id") val triviaID:Int,
    val state:String,
    var consecutive:Int?
)
