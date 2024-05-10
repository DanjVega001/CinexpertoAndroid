package com.cinexperto.app.features.auth.ui.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cinexperto.app.HomeScreenActivity
import com.cinexperto.app.R
import com.cinexperto.app.core.constants.Functions
import com.cinexperto.app.core.fragments.ProgressFragment
import com.cinexperto.app.databinding.ActivityVerificationEmailBinding
import com.cinexperto.app.features.auth.ui.viewmodels.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationEmailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVerificationEmailBinding
    private val signupViewModel: SignupViewModel by viewModels()
    private var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        loading()
        focusEditText()
        setupButtons()
        fetchResult()
    }

    private fun loading(){
        val fragmentLoading = ProgressFragment()
        signupViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.headerValidateCode.visibility = View.GONE
                binding.bodyValidateCode.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_validate_code, fragmentLoading)
                    .commit()
            } else {
                binding.headerValidateCode.visibility = View.VISIBLE
                binding.bodyValidateCode.visibility = View.VISIBLE
            }
        })
    }

    private fun setupButtons() {
        binding.btnBackToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.btnVerifyCode.setOnClickListener {
            signupViewModel.signup(recoverCode())
        }
    }

    private fun fetchResult() {
        signupViewModel.isError.observe(this, Observer {
            isError = it
        })

        signupViewModel.message.observe(this, Observer {
            if (!isError) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun recoverCode():Int{
        val code1 = binding.etxtCode1.text.trim().toString()
        val code2 = binding.etxtCode2.text.trim().toString()
        val code3 = binding.etxtCode3.text.trim().toString()
        val code4 = binding.etxtCode4.text.trim().toString()
        val code5 = binding.etxtCode5.text.trim().toString()
        val code6 = binding.etxtCode6.text.trim().toString()
        return "$code1$code2$code3$code4$code5$code6".toInt()
    }


    private fun focusEditText(){
        for (i in 1..6) {
            Functions.nextFocusEditText(binding, i)
        }
    }
}