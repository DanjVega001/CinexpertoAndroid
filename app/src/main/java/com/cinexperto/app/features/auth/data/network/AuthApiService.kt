package com.cinexperto.app.features.auth.data.network

import android.util.Log
import arrow.core.Either
import com.cinexperto.app.core.constants.Functions
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.data.models.AuthResponse
import com.cinexperto.app.features.auth.data.models.VerificationMailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class AuthApiService @Inject constructor(private val apiClient: AuthApiClient) {

    suspend fun signup(data:AuthDto) : Either<String, String>{
        return withContext(Dispatchers.IO) {
            val response = apiClient.signup(data)
            if (response.isSuccessful) {
                Either.Right(response.body()!!["message"]!!)
            } else {
                val errorMessage = response.errorBody()!!.string()
                Either.Left(Functions.parseErrorToJson(errorMessage))
            }
        }
    }

    suspend fun login(data:AuthDto) : Either<String, AuthResponse> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.login(data)
            if (response.isSuccessful) {
                Either.Right(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string()
                Either.Left(Functions.parseErrorToJson(errorBody))
            }
        }
    }

    suspend fun sendVerificationMail(email:String, username:String) : Either<String, VerificationMailResponse> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.sendVerificationEmail(email, username)
            if (response.isSuccessful) {
                Either.Right(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string()
                Either.Left(Functions.parseErrorToJson(errorBody))
            }
        }
    }
}