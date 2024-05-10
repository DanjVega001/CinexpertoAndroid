package com.cinexperto.app.features.auth.data.repositories

import arrow.core.Either
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.data.models.AuthResponse
import com.cinexperto.app.features.auth.data.models.VerificationMailResponse
import com.cinexperto.app.features.auth.data.network.AuthApiService
import com.cinexperto.app.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val service:AuthApiService) : AuthRepository  {

    override suspend fun signup(data: AuthDto): Either<String, String> = service.signup(data)

    override suspend fun login(data: AuthDto): Either<String, AuthResponse> = service.login(data)

    override suspend fun sendVerificationEmail(email: String, username:String): Either<String, VerificationMailResponse>
        = service.sendVerificationMail(email, username)

}