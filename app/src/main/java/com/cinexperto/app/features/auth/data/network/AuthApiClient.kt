package com.cinexperto.app.features.auth.data.network

import arrow.core.Either
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.data.models.AuthResponse
import com.cinexperto.app.features.auth.data.models.VerificationMailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiClient {

    @POST("signup")
    suspend fun signup(@Body data:AuthDto):Response<Map<String, String>>

    @POST("login")
    suspend fun login(@Body data:AuthDto):Response<AuthResponse>

    @POST("verification-email")
    @FormUrlEncoded
    suspend fun sendVerificationEmail(@Field("email") email:String, @Field("name") username:String):Response<VerificationMailResponse>

}