package com.cinexperto.app.features.trivia.domain.usecases

import arrow.core.Either
import com.cinexperto.app.features.trivia.data.models.PublishedTrivia
import com.cinexperto.app.features.trivia.domain.repositories.TriviaRepository
import javax.inject.Inject

class GetPublishedTriviasUsecase @Inject constructor(private val repository: TriviaRepository) {

    suspend operator fun invoke(state:String, token:String): Either<String, List<PublishedTrivia>>{
        return repository.getPublishedTrivas(state, token)
    }

}