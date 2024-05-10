package com.cinexperto.app.features.auth.ui.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cinexperto.app.MainActivity
import com.cinexperto.app.R
import com.cinexperto.app.core.fragments.ProgressFragment
import com.cinexperto.app.databinding.ActivityRegisterBinding
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.ui.viewmodels.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private val signupViewModel: SignupViewModel by viewModels()
    private var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        loading()
        setupButtons()
        messageValidator()
        fetchResult()
    }

    private fun loading(){
        val fragmentLoading = ProgressFragment()
        signupViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.headerRegister.visibility = View.GONE
                binding.bodyRegister.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, fragmentLoading)
                    .commit()
            } else {
                binding.headerRegister.visibility = View.VISIBLE
                binding.bodyRegister.visibility = View.VISIBLE
            }
        })
    }

    private fun fetchResult() {
        signupViewModel.isError.observe(this, Observer {
            isError = it
        })

        signupViewModel.message.observe(this, Observer {
            if (!isError) {
                startActivity(Intent(this, VerificationEmailActivity::class.java))
            }
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    private fun messageValidator() {
        binding.etxtPassword.setOnFocusChangeListener {_, hasFocus ->
            if (hasFocus) {
                binding.txtValPassword.visibility = View.VISIBLE
            }
        }

        binding.etxtRepeatPassword.setOnFocusChangeListener {_, hasFocus ->
            if (hasFocus) {
                binding.txtValRepeatPassword.visibility = View.VISIBLE
            }
        }
    }

    private fun setupButtons(){
        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnCreateAccount.setOnClickListener {
            signupViewModel.sendVerificationEmail(AuthDto(
                binding.etxtUsername.text.toString().trim(),
                binding.etxtEmail.text.toString().trim(),
                binding.etxtPassword.text.toString().trim()
            ), binding.etxtRepeatPassword.text.toString().trim())
        }

    }
}