package com.cinexperto.app.features.trivia.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cinexperto.app.R
import com.cinexperto.app.features.trivia.data.models.GetTriviasDto

class ItemTriviaAdapter(private val trivias: List<GetTriviasDto>, private val color: Int) : RecyclerView.Adapter<TriviaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TriviaViewHolder(layoutInflater.inflate(R.layout.item_rv_trivias_one, parent, false), color)
    }

    override fun getItemCount(): Int {
        return trivias.size
    }

    override fun onBindViewHolder(holder: TriviaViewHolder, position: Int) {
        holder.bind(trivias[position])
    }


}