package com.cinexperto.app.features.trivia.domain.repositories

import arrow.core.Either
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import com.cinexperto.app.features.trivia.data.models.PublishedTrivia
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.Header

interface TriviaRepository {

    suspend fun getTriviasByLevelID(levelID:Int, token:String): Either<String, List<GetTriviasDto>>

    suspend fun getPublishedTrivas(state:String, token:String): Either<String, List<PublishedTrivia>>

}