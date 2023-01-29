package com.example.gameapp.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager1
import com.example.gameapp.R
import com.example.gameapp.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewGameHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameUser = view.findViewById<TextView>(R.id.textView6)
    val reviewUser = view.findViewById<TextView>(R.id.textView7)

    fun bindValue(v: Review){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.Default) {
                    NetworkManager1.getUserInfo(v.author.steamId)
                }
                reviewUser.text = v.review
                nameUser.text = response.response.players?.get(0)?.personaname
            }catch (e: Exception){

            }
        }
    }
}