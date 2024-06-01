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
import com.cinexperto.app.databinding.ActivityPublishedTriviasAcceptedBinding
import com.cinexperto.app.databinding.ActivityPublishedTriviasPendingBinding
import com.cinexperto.app.features.trivia.ui.viewmodels.GetPublishedTriviasViewModel
import com.cinexperto.app.features.trivia.ui.views.adapters.ItemPublishedTriviaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishedTriviasPending : AppCompatActivity() {



    private lateinit var binding: ActivityPublishedTriviasPendingBinding
    private val viewModel: GetPublishedTriviasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublishedTriviasPendingBinding.inflate(layoutInflater)
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
        binding.btnBackToHomeFromPublishedTrvPending.setOnClickListener {
            startActivity(Intent(this, PublishedTriviaActivity::class.java))
            finish()
        }
    }

    private fun fetchData(){
        viewModel.data.observe(this, Observer {
            if (it.isNotEmpty()) {
                binding.rvPTrvPending.visibility = View.VISIBLE
                binding.rvPTrvPending.setHasFixedSize(true)
                binding.rvPTrvPending.layoutManager = LinearLayoutManager(this)
                binding.rvPTrvPending.adapter = ItemPublishedTriviaAdapter(
                    it,
                    ContextCompat.getColor(this, R.color.color_pendientes_prtv)
                )
            } else {
                binding.txtMessageEmptyList.visibility = View.VISIBLE
                binding.rvPTrvPending.visibility = View.GONE
            }
        })
    }

    private fun fetchError() {
        viewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initRequest(){
        viewModel.getPublishedTrivias(Constants.PENDING_STATE, Functions.getAccessToken(this))
    }

    private fun loading(){
        val fragment = ProgressFragment()
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.rvPTrvPending.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_pTrv_pending, fragment)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .remove(fragment).commit()
            }
        })
    }
}