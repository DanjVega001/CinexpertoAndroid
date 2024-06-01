package com.cinexperto.app.features.trivia.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cinexperto.app.R
import com.cinexperto.app.features.trivia.data.models.PublishedTrivia

class ItemPublishedTriviaAdapter(private val trivias:List<PublishedTrivia>, private val color:Int) : RecyclerView.Adapter<PublishedTriviaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublishedTriviaViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return PublishedTriviaViewHolder(layoutInflater.inflate(R.layout.item_rv_published_trivias, parent, false))
    }

    override fun getItemCount(): Int = trivias.size

    override fun onBindViewHolder(holder: PublishedTriviaViewHolder, position: Int) {
        holder.bind(trivias[position], color)
    }


}