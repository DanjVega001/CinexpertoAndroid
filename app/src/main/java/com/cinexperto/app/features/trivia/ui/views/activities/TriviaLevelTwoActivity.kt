package com.cinexperto.app.features.trivia.ui.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cinexperto.app.R
import com.cinexperto.app.core.constants.Constants
import com.cinexperto.app.core.constants.Functions
import com.cinexperto.app.core.fragments.ProgressFragment
import com.cinexperto.app.databinding.ActivityTriviaLevelTwoBinding
import com.cinexperto.app.features.trivia.ui.viewmodels.GetTriviasViewModel
import com.cinexperto.app.features.trivia.ui.views.adapters.ItemTriviaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriviaLevelTwoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTriviaLevelTwoBinding
    private val getTriviasViewModel: GetTriviasViewModel by viewModels()
    private var isError = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTriviaLevelTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initData()
        loading()
        fetchError()
        fetchData()
        setupButtons()
    }

    private fun initData(){
        getTriviasViewModel.getTrivias(Constants.LEVEL_ID_TWO, Functions.getAccessToken(this))
    }

    private fun fetchError() {
        getTriviasViewModel.isError.observe(this, Observer {
            isError = it
        })

        getTriviasViewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun fetchData() {
        getTriviasViewModel.data.observe(this, Observer {
            binding.rvTriviaLevelTwo.setHasFixedSize(true)
            binding.rvTriviaLevelTwo.layoutManager = LinearLayoutManager(this)
            binding.rvTriviaLevelTwo.adapter = ItemTriviaAdapter(it, ContextCompat.getColor(this, R.color.color_lvl2))
        })
    }

    private fun loading(){
        val fragmentLoading = ProgressFragment()
        getTriviasViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.rvTriviaLevelTwo.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_getTrivias_lvl_2, fragmentLoading)
                    .commit()
            } else {
                binding.rvTriviaLevelTwo.visibility = View.VISIBLE
            }
        })
    }

    private fun setupButtons(){
        binding.btnBackToLevelsTrvFromTrvTwo.setOnClickListener {
            startActivity(Intent(this, AnswerTriviaActivity::class.java))
            finish()
        }
    }
}