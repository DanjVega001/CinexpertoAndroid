package com.cinexperto.app.features.trivia.domain.usecases

import arrow.core.Either
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import com.cinexperto.app.features.trivia.domain.repositories.TriviaRepository
import javax.inject.Inject

class GetTriviasUsecase @Inject constructor(private val repository: TriviaRepository) {

    suspend operator fun invoke(levelID:Int, token:String) : Either<String, List<GetTriviasDto>> {
        return repository.getTriviasByLevelID(levelID, token)
    }
}