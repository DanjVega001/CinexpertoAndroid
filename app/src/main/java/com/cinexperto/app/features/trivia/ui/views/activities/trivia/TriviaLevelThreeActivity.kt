package com.cinexperto.app.features.trivia.ui.views.activities.trivia

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
import com.cinexperto.app.databinding.ActivityTriviaLevelThreeBinding
import com.cinexperto.app.features.trivia.ui.viewmodels.GetTriviasViewModel
import com.cinexperto.app.features.trivia.ui.views.adapters.ItemTriviaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriviaLevelThreeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTriviaLevelThreeBinding
    private val getTriviasViewModel: GetTriviasViewModel by viewModels()
    private var isError = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTriviaLevelThreeBinding.inflate(layoutInflater)
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
        getTriviasViewModel.getTrivias(Constants.LEVEL_ID_THREE, Functions.getAccessToken(this))
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
            binding.rvTriviaLevelThree.setHasFixedSize(true)
            binding.rvTriviaLevelThree.layoutManager = LinearLayoutManager(this)
            binding.rvTriviaLevelThree.adapter = ItemTriviaAdapter(it, ContextCompat.getColor(this, R.color.color_lvl3))
        })
    }

    private fun loading(){
        val fragmentLoading = ProgressFragment()
        getTriviasViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.rvTriviaLevelThree.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_getTrivias_lvl_3, fragmentLoading)
                    .commit()
            } else {
                binding.rvTriviaLevelThree.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .remove(fragmentLoading).commit()
            }
        })
    }

    private fun setupButtons(){
        binding.btnBackToLevelsTrvFromTrvThree.setOnClickListener {
            startActivity(Intent(this, AnswerTriviaActivity::class.java))
            finish()
        }
    }
}