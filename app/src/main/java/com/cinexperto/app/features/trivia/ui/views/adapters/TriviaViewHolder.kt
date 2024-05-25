package com.cinexperto.app.features.trivia.ui.views.adapters

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cinexperto.app.R
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto

class TriviaViewHolder(view: View, private val color: Int) : ViewHolder(view) {

    private val btnSingleTriviaLvlOneAnswered:Button = view.findViewById(R.id.btnSingleTriviaLvlOneAnswered)
    private val btnSingleTriviaLvlOne:Button = view.findViewById(R.id.btnSingleTriviaLvlOne)
    private val correct:ImageView = view.findViewById(R.id.correct)
    private val incorrect:ImageView = view.findViewById(R.id.incorrect)


    fun bind (item:GetTriviasDto) {
        if (item.state == "pendiente") {
            btnSingleTriviaLvlOne.setBackgroundColor(color)
            btnSingleTriviaLvlOne.text = item.consecutive.toString()
            btnSingleTriviaLvlOne.visibility = View.VISIBLE
            btnSingleTriviaLvlOneAnswered.visibility = View.GONE
        } else {
            btnSingleTriviaLvlOneAnswered.text = item.consecutive.toString()
            btnSingleTriviaLvlOneAnswered.visibility = View.VISIBLE
            btnSingleTriviaLvlOne.visibility = View.GONE
            btnSingleTriviaLvlOneAnswered.isEnabled = false
            correct.visibility = View.GONE
            incorrect.visibility = View.GONE
            if (item.state == "correcto") correct.visibility = View.VISIBLE
            else incorrect.visibility = View.VISIBLE
        }
    }
}