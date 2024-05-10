package com.cinexperto.app.features.auth.ui.viewmodels

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.domain.usecases.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUsecase
) : ViewModel() {

    val isError = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()
    val accessToken = MutableLiveData<String>()

    fun login(data:AuthDto){
        val validate = validateData(data)
        if (validate!=null) {
            isError.postValue(true)
            message.postValue(validate)
            return
        }
        isLoading.postValue(true)
        viewModelScope.launch{
            val response = loginUsecase(data)
            response.fold(
                { error ->
                    isError.postValue(true)
                    message.postValue(error)
                },
                { res ->
                    isError.postValue(false)
                    accessToken.postValue(res.accessToken)
                }
            )
            isLoading.postValue(false)
        }
    }

    private fun validateData(data: AuthDto) :String?{
        if (data.email.isEmpty()) return "El email no puede estar vacío"
        if (!Patterns.EMAIL_ADDRESS.matcher(data.email).matches()) return "Email inválido"
        if (data.password.isEmpty()) return "La contraseña no puede estar vacía"
        return null
    }

}