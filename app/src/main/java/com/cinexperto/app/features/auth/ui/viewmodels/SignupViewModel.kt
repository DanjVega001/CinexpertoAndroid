package com.cinexperto.app.features.auth.ui.viewmodels

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinexperto.app.core.constants.Functions
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.domain.usecases.LoginUsecase
import com.cinexperto.app.features.auth.domain.usecases.SignupUsecase
import com.cinexperto.app.features.auth.domain.usecases.VerificationEmailUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUsecase: SignupUsecase,
    private val verificationEmailUsecase: VerificationEmailUsecase
) : ViewModel() {

    companion object {
        private var data:AuthDto ? = null
        private var sendCode:Int = 0
    }

    val isError = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    fun sendVerificationEmail (data: AuthDto, confirmPwd:String) {
        val validate = validateForm(data, confirmPwd)
        if (validate!=null) {
            isError.postValue(true)
            message.postValue(validate)
        } else {
            isLoading.postValue(true)
            viewModelScope.launch {
                val result = verificationEmailUsecase(data.email, data
                    .username!!)
                result.fold(
                    { error ->
                        isError.postValue(true)
                        message.postValue(error)
                    },
                    { res ->
                        isError.postValue(false)
                        message.postValue(res.message)
                        sendCode = res.code
                        SignupViewModel.data = data
                    }
                )
                isLoading.postValue(false)
            }


        }
    }

    fun signup(code:Int) {
        Log.d("efef",code.toString())
        Log.d("efef",sendCode.toString())
        if (data != null && code == sendCode) {
            isLoading.postValue(true)
            viewModelScope.launch {
                val result = signupUsecase(data!!)
                isLoading.postValue(false)
                result.fold(
                    { error ->
                        isError.postValue(true)
                        message.postValue(error)
                    },
                    { resultMessage ->
                        isError.postValue(false)
                        message.postValue(resultMessage)
                    }
                )
            }
        } else {
            isError.postValue(true)
            message.postValue("Los códigos no coinciden.")
        }
    }

    private fun validateForm(data:AuthDto, confirmPwd: String):String?{
        if (data.password != confirmPwd) return "Las contraseñas no coinciden"
        if (!Patterns.EMAIL_ADDRESS.matcher(data.email).matches()) return "Email inválido"
        if (data.username!!.length < 5) return "El nombre de usuario debe tener minimo 4 caracteres"
        if (!Functions.isValidPassword(data.password)) return "La contraseña no es valida"
        return null
    }

}