package com.cinexperto.app.features.trivia.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinexperto.app.features.auth.ui.viewmodels.SignupViewModel
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto
import com.cinexperto.app.features.trivia.domain.usecases.GetTriviasUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GetTriviasViewModel @Inject constructor(
    private val usecase: GetTriviasUsecase
) : ViewModel() {

    val isError = MutableLiveData<Boolean>()
    val data = MutableLiveData<List<GetTriviasDto>>()
    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()

    fun getTrivias(levelID:Int, token:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = usecase(levelID, token)
            result.fold(
                { error ->
                    isError.postValue(true)
                    message.postValue(error)
                },
                { res ->
                    isError.postValue(false)
                    var i = 1
                    res.forEach {
                        it.consecutive = i
                        i++
                    }
                    data.postValue(res)
                }
            )
            isLoading.postValue(false)
        }
    }
}