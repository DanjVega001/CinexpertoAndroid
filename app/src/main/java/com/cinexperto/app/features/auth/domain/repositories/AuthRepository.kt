package com.cinexperto.app.features.auth.domain.repositories

import arrow.core.Either
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.data.models.AuthResponse
import com.cinexperto.app.features.auth.data.models.VerificationMailResponse

interface AuthRepository {

    suspend fun signup (data:AuthDto) : Either<String, String>

    suspend fun login (data:AuthDto) : Either<String, AuthResponse>

    suspend fun sendVerificationEmail (email:String, username:String) : Either<String, VerificationMailResponse>

}