package com.cinexperto.app.features.trivia.ui.views.activities.published_trivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cinexperto.app.HomeScreenActivity
import com.cinexperto.app.databinding.ActivityPublishedTriviaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishedTriviaActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPublishedTriviaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublishedTriviaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupButtons()
    }

    private fun setupButtons(){
        binding.btnBackToHomeFromPublishedTrv.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        }
        binding.btnGoPTriviasAceptadas.setOnClickListener {
            startActivity(Intent(this, PublishedTriviasAccepted::class.java))
            finish()
        }
        binding.btnGoPTriviasRehazadas.setOnClickListener {
            startActivity(Intent(this, PublishedTriviasRejected::class.java))
            finish()
        }
        binding.btnGoPTriviasPendientes.setOnClickListener {
            startActivity(Intent(this, PublishedTriviasPending::class.java))
            finish()
        }
    }
}