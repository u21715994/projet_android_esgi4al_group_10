package com.example.gameapp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.adapter.ReviewGameAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avis_du_jeu)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val gameReviewView = findViewById<View>(R.id.recycler_view) as RecyclerView

                val response = withContext(Dispatchers.IO) {
                    NetworkManager2.getReviewGame("431960")
                }
                val adapter = ReviewGameAdapter(response.reviews)
                gameReviewView.layoutManager = LinearLayoutManager(applicationContext)
                // Attach the adapter to the recyclerview to populate items
                gameReviewView.adapter = adapter
            } catch (e: Exception) {

            }
        }
    }
}
