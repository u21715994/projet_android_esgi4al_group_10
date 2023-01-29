package com.example.gameapp.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.Rank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameListHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameGame = view.findViewById<TextView>(R.id.textView2)
    val editors = view.findViewById<TextView>(R.id.textView3)

    fun bindValue(rank: Rank){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.Default) {
                    NetworkManager2.getDetailsGame(rank.appId.toString())
                }
                nameGame.text = response._570.data.name
                editors.text = response._570.data.publishers.toString()
            }catch (e: Exception){

            }
        }
    }
}