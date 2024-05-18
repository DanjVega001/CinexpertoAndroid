package com.cinexperto.app.core.constants

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import arrow.core.Either
import com.cinexperto.app.R
import com.cinexperto.app.core.util.ManageSharedPreferences
import com.cinexperto.app.databinding.ActivityVerificationEmailBinding
import org.json.JSONObject

object Functions {

    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("""^(?=.*[a-z])(?=.*[0-9]).{6,}$""")
        return passwordRegex.matches(password)
    }

    fun parseErrorToJson(errorBody:String?) :String{
        return if (!errorBody.isNullOrEmpty()) {
            val jsonObject = JSONObject(errorBody)
            val errorMessage = jsonObject.getString("error")
            errorMessage
        } else {
            "Error desconocido"
        }
    }

    private fun switchEtxt(position: Int, binding: ActivityVerificationEmailBinding):EditText?{
        return when (position) {
            1 -> binding.etxtCode1
            2 -> binding.etxtCode2
            3 -> binding.etxtCode3
            4 -> binding.etxtCode4
            5 -> binding.etxtCode5
            6 -> binding.etxtCode6
            else -> null
        }
    }

    fun nextFocusEditText(binding:ActivityVerificationEmailBinding, position:Int){

        val eText = switchEtxt(position, binding) ?: return

        eText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isEmpty() && before == 1 && position != 1) {
                    val eTxtBefore = switchEtxt(position-1, binding) ?: return
                    eTxtBefore.requestFocus()
                } else if (s.length == 1 && position != 6) {
                    val eTxtNext = switchEtxt(position+1, binding) ?: return
                    eTxtNext.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun getAccessToken(context: Context):String{
        return "Bearer ${ManageSharedPreferences.getPreferences("token", context)}"
    }

}