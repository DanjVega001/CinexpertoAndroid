package com.cinexperto.app.features.auth.ui.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.cinexperto.app.HomeScreenActivity
import com.cinexperto.app.MainActivity
import com.cinexperto.app.R
import com.cinexperto.app.core.fragments.ProgressFragment
import com.cinexperto.app.core.util.ManageSharedPreferences
import com.cinexperto.app.databinding.ActivityLoginBinding
import com.cinexperto.app.features.auth.data.models.AuthDto
import com.cinexperto.app.features.auth.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private val loginViewModel:LoginViewModel by viewModels()
    private var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        loading()
        setupButtons()
        fetchError()
        fetchData()
    }

    private fun fetchData(){
        loginViewModel.accessToken.observe(this, Observer {
            ManageSharedPreferences.savePreferences("token", it, this)
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        })
    }

    private fun loading(){
        val fragmentLoading = ProgressFragment()
        loginViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.headerLogin.visibility = View.GONE
                binding.bodyLogin.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_login, fragmentLoading)
                    .commit()
            } else {
                binding.headerLogin.visibility = View.VISIBLE
                binding.bodyLogin.visibility = View.VISIBLE
            }
        })
    }

    private fun fetchError() {
        loginViewModel.isError.observe(this, Observer {
            isError = it
        })

        loginViewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupButtons(){
        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            loginViewModel.login(getLoginData())
        }
    }

    private fun getLoginData():AuthDto{
        return AuthDto(
            username = null,
            email = binding.etxtEmailLogin.text.trim().toString(),
            password = binding.etxtPasswordLogin.text.trim().toString()
        )
    }
}