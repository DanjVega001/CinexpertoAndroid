package com.cinexperto.app.features.trivia.domain.repositories

import arrow.core.Either
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto

interface TriviaRepository {

    suspend fun getTriviasByLevelID(levelID:Int, token:String): Either<String, List<GetTriviasDto>>
}