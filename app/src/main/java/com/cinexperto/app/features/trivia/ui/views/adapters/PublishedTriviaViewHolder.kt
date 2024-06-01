package com.cinexperto.app.features.trivia.ui.views.adapters

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cinexperto.app.R
import com.cinexperto.app.features.trivia.data.models.PublishedTrivia

class PublishedTriviaViewHolder(view: View):ViewHolder(view) {

    private val txtQuestionPTrv : TextView = view.findViewById(R.id.txtQuestionPTrv)
    private val btnMorePTrv:Button = view.findViewById(R.id.btnMorePTrv)

    fun bind(item: PublishedTrivia, color: Int) {
        btnMorePTrv.setBackgroundColor(color)
        txtQuestionPTrv.text = item.question
    }
}