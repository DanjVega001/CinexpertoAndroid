package com.cinexperto.app.features.trivia.ui.views.activities.published_trivia

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
import com.cinexperto.app.databinding.ActivityPublihedTriviasRejectedBinding
import com.cinexperto.app.databinding.ActivityPublishedTriviasAcceptedBinding
import com.cinexperto.app.features.trivia.ui.viewmodels.GetPublishedTriviasViewModel
import com.cinexperto.app.features.trivia.ui.views.adapters.ItemPublishedTriviaAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PublishedTriviasRejected : AppCompatActivity() {


    private lateinit var binding: ActivityPublihedTriviasRejectedBinding
    private val viewModel: GetPublishedTriviasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublihedTriviasRejectedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initRequest()
        loading()
        fetchError()
        fetchData()
        setupButtons()
    }

    private fun setupButtons(){
        binding.btnBackToHomeFromPublishedTrvRejected.setOnClickListener {
            startActivity(Intent(this, PublishedTriviaActivity::class.java))
            finish()
        }
    }

    private fun fetchData(){
        viewModel.data.observe(this, Observer {
            if (it.isNotEmpty()) {
                binding.rvPTrvRejected.visibility = View.VISIBLE
                binding.rvPTrvRejected.setHasFixedSize(true)
                binding.rvPTrvRejected.layoutManager = LinearLayoutManager(this)
                binding.rvPTrvRejected.adapter = ItemPublishedTriviaAdapter(
                    it,
                    ContextCompat.getColor(this, R.color.color_rechazadas_prtv)
                )
            } else {
                binding.txtMessageEmptyList.visibility = View.VISIBLE
                binding.rvPTrvRejected.visibility = View.GONE
            }
        })
    }

    private fun fetchError() {
        viewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initRequest(){
        viewModel.getPublishedTrivias(Constants.REJECTED_STATE, Functions.getAccessToken(this))
    }

    private fun loading(){
        val fragment = ProgressFragment()
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.rvPTrvRejected.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_pTrv_rejected, fragment)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .remove(fragment).commit()
            }
        })
    }
}