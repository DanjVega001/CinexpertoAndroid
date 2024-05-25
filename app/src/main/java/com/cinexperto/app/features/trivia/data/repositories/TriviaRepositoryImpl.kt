package com.cinexperto.app.features.trivia.data.repositories

import arrow.core.Either
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import com.cinexperto.app.features.trivia.data.models.PublishedTrivia
import com.cinexperto.app.features.trivia.data.network.TriviaApiService
import com.cinexperto.app.features.trivia.domain.repositories.TriviaRepository
import retrofit2.Response
import javax.inject.Inject

class TriviaRepositoryImpl @Inject constructor(
    private val service:TriviaApiService
) : TriviaRepository {
    override suspend fun getTriviasByLevelID(levelID: Int, token:String): Either<String, List<GetTriviasDto>> {
        return service.getTriviasByLevelID(levelID, token)
    }

    override suspend fun getPublishedTrivas(
        state: String,
        token: String
    ): Either<String, List<PublishedTrivia>> {
        return service.getPublishedTrivias(state, token)
    }
}