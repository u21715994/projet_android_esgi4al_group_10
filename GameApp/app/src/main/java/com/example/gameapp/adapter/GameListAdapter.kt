package com.example.gameapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.R
import com.example.gameapp.Rank
import com.example.gameapp.holder.GameListHolder

class GameListAdapter(val values: List<Rank>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GameListHolder(inflater.inflate(
            R.layout.game_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GameListHolder)
            .bindValue(values[position])
        (holder as GameListHolder)
            .clickOnButton(values[position])
    }
}
