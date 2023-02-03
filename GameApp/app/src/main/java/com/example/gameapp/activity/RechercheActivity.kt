package com.example.gameapp.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager1
import com.example.gameapp.R
import com.example.gameapp.adapter.GameListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RechercheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recherche)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RechercheFragment())
            .addToBackStack("recherche")
            .commit()
    }

    class RechercheFragment: Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.recherche_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val gameReviewView = view.findViewById<View>(R.id.recycler_view_recherche) as RecyclerView

                    val response = withContext(Dispatchers.IO) {
                        NetworkManager1.getMostPlayedGames()
                    }

                    val editText: EditText = view.findViewById(R.id.editText)
                    val search = view.findViewById<ImageView>(R.id.search)
                    search.setOnClickListener(View.OnClickListener {
                        Log.i("search", editText.text.toString())
                    })

                    val adapter = GameListAdapter(response.response.ranks)
                    gameReviewView.layoutManager = LinearLayoutManager(context)
                    gameReviewView.adapter = adapter
                } catch (e: Exception) {
                    Log.e("Err", e.toString())
                }
            }
        }
    }
}
