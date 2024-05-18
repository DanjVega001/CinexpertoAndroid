package com.cinexperto.app.features.trivia.data.network

import arrow.core.Either
import com.cinexperto.app.core.constants.Functions
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TriviaApiService @Inject constructor(private val apiClient: TriviaApiClient){

    suspend fun getTriviasByLevelID(levelID:Int, token:String):Either<String, List<GetTriviasDto>>{
        return withContext(Dispatchers.IO){
            val response = apiClient.getTriviasByLevelID(levelID, token)
            if (response.isSuccessful) {
                Either.Right(response.body()!!)
            } else {
                val errorMessage = response.errorBody()!!.string()
                Either.Left(Functions.parseErrorToJson(errorMessage))
            }
        }
    }
}