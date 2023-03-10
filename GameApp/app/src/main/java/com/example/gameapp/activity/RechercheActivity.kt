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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.NetworkManager1
import com.example.gameapp.NetworkManager3
import com.example.gameapp.R
import com.example.gameapp.adapter.GameListAdapter
import com.example.gameapp.adapter.GameListSearchAdapter
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

            val gameReviewView = view.findViewById<View>(R.id.recycler_view_recherche) as RecyclerView
            val nbSearch = view.findViewById<TextView>(R.id.textView999)
            nbSearch.text = "Nombre de résultats : 0"
            val search = view.findViewById<ImageView>(R.id.search)
            search.setOnClickListener(View.OnClickListener {
                val editText: EditText = view.findViewById(R.id.editText)
                GlobalScope.launch(Dispatchers.Main) {
                    val response = withContext(Dispatchers.IO) {
                        NetworkManager3.getSearchGames(editText.text.toString())

                    }
                    val adapter = GameListSearchAdapter(response)
                    gameReviewView.layoutManager = LinearLayoutManager(context)
                    gameReviewView.adapter = adapter
                    nbSearch.text = "Nombre de résultats : ${response.size}"
                }
            })
        }
    }
}
