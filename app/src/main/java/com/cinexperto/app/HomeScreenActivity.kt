package com.cinexperto.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cinexperto.app.databinding.ActivityHomeScreenBinding
import com.cinexperto.app.features.trivia.ui.views.activities.AnswerTriviaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupButtons()
    }

    private fun setupButtons(){
        binding.btnGoAnswerTrivia.setOnClickListener {
            startActivity(Intent(this, AnswerTriviaActivity::class.java))
            //finish()
        }
    }
}