package com.cinexperto.app.features.trivia.data.network

import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TriviaApiClient {

    @GET("trivias/{levelID}")
    suspend fun getTriviasByLevelID(@Path("levelID") levelID:Int, @Header("Authorization") token:String):Response<List<GetTriviasDto>>
}