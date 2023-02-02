package com.example.gameapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.adapter.ReviewGameAdapter
import com.example.gameapp.backend.UserAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inscription)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val email = findViewById<EditText>(R.id.editText).text.toString()
            val password = findViewById<EditText>(R.id.editText2).text.toString()
            val username = findViewById<EditText>(R.id.editText3).text.toString()
            val userData = mapOf("username" to username)
            val auth = UserAuth()
            if(auth.createU(email, password, userData,this)){
                //action à réaliser quand l'utilisateur est inscrit

            }
            else{
                //Action à réaliser quand l'utilisateur n'est pas inscrit


            }
        }
    }
}