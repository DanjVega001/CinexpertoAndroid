package com.cinexperto.app.features.auth.domain.usecases

import arrow.core.Either
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class SignupUsecase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(data:AuthDto) : Either<String, String> = repository.signup(data)

}