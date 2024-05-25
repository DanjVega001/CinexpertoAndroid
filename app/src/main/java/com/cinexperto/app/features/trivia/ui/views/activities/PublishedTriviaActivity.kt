package com.cinexperto.app.features.trivia.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cinexperto.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishedTriviaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_published_trivia)
    }
}