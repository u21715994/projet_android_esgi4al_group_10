package com.example.gameapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager1
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.adapter.GameListAdapter
import com.example.gameapp.adapter.ReviewGameAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishlistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wishlist)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val gameReviewView = findViewById<View>(R.id.recycler_view_wishlist) as RecyclerView

                val response = withContext(Dispatchers.IO) {
                    NetworkManager1.getMostPlayedGames()
                }
                val adapter = GameListAdapter(response.response.ranks)
                gameReviewView.layoutManager = LinearLayoutManager(applicationContext)
                // Attach the adapter to the recyclerview to populate items
                gameReviewView.adapter = adapter
            } catch (e: Exception) {

            }
        }
    }
}