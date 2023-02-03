package com.example.gameapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.R
import com.example.gameapp.Search
import com.example.gameapp.holder.GameListSearchHolder

class GameListSearchAdapter(val values: Array<Search>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GameListSearchHolder(inflater.inflate(
            R.layout.game_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GameListSearchHolder)
            .bindValue(values[position])
        (holder as GameListSearchHolder)
            .clickOnButton(values[position])
    }
}
