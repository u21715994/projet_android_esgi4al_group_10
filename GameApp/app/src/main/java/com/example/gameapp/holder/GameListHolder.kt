package com.example.gameapp.holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.Rank
import com.example.gameapp.activity.DescriptionGameActivity
import com.example.gameapp.activity.RechercheActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GameListHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameGame = view.findViewById<TextView>(R.id.textView2)
    val editors = view.findViewById<TextView>(R.id.textView3)
    val price = view.findViewById<TextView>(R.id.textView4)
    val button = view.findViewById<Button>(R.id.more_information)
    val imageGameBackground = view.findViewById<ImageView>(R.id.imageView2)
    val imageGame = view.findViewById<ImageView>(R.id.imageView3)
    val context = view.context
    fun bindValue(rank: Rank){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.Default) {
                    NetworkManager2.getDetailsGame(rank.appId.toString())
                }
                nameGame.text = response.gameDetail.data.name
                editors.text = response.gameDetail.data.publishers.toString()
                if(response.gameDetail.data.isFree)
                    price.text = "Gratuit"
                else
                    price.text = response.gameDetail.data.price_overview.final_formatted
                Glide.with(context).load(response.gameDetail.data.backgroundRaw).into(imageGameBackground);
                Glide.with(context).load(response.gameDetail.data.headerImage).into(imageGame);
            }catch (e: Exception){

            }
        }
    }

    fun clickOnButton(rank: Rank){
        button.setOnClickListener {
            (itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DescriptionGameActivity.DescriptionGameFragment.newInstance(rank.appId.toString()))
                .commit()
        }
    }
}