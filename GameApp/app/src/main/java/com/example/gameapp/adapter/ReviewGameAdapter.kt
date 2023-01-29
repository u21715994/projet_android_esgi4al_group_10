package com.example.gameapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.R
import com.example.gameapp.Review
import com.example.gameapp.holder.ReviewGameHolder

class ReviewGameAdapter(val values: List<Review>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewGameHolder(inflater.inflate(
            R.layout.review_game_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReviewGameHolder)
            .bindValue(values[position])
    }
}