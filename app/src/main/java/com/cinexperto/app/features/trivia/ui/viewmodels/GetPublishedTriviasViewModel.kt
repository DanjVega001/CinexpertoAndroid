package com.cinexperto.app.features.trivia.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import com.cinexperto.app.features.trivia.data.models.PublishedTrivia
import com.cinexperto.app.features.trivia.domain.usecases.GetPublishedTriviasUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetPublishedTriviasViewModel @Inject constructor(
    private val usecase: GetPublishedTriviasUsecase
):ViewModel() {

    val data = MutableLiveData<List<PublishedTrivia>>()
    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()

    fun getPublishedTrivias(state:String, token:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = usecase(state, token)
            result.fold(
                { error ->
                    message.postValue(error)
                },
                { res ->
                    data.postValue(res)
                }
            )
            isLoading.postValue(false)
        }
    }

}