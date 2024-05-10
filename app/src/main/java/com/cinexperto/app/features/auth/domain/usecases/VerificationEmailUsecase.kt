package com.cinexperto.app.features.auth.domain.usecases

import arrow.core.Either
import com.cinexperto.app.features.auth.data.models.VerificationMailResponse
import com.cinexperto.app.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class VerificationEmailUsecase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, username:String) : Either<String, VerificationMailResponse>
        = repository.sendVerificationEmail(email, username)

}