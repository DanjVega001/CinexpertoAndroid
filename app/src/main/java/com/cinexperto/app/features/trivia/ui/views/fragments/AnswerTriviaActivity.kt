package com.cinexperto.app.features.trivia.ui.views.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cinexperto.app.HomeScreenActivity
import com.cinexperto.app.databinding.ActivityAnswerTriviaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnswerTriviaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnswerTriviaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerTriviaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupButtons()
    }

    private fun setupButtons(){
        binding.btnBackToHomeFromTrv.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        }
    }


}