package com.example.gameapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameapp.NetworkManager1
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.adapter.GameListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccueilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accueil)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AccueilFragment())
            .addToBackStack("accueil")
            .commit()
    }

    class AccueilFragment: Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.accueil_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val fragment = this

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val gameReviewView = view.findViewById<View>(R.id.recycler_view_list_game) as RecyclerView

                    val response = withContext(Dispatchers.IO) {
                        NetworkManager1.getMostPlayedGames()
                    }

                    val gameID = response.response.ranks[0].appId.toString()

                    val gameDetailFirst = withContext(Dispatchers.IO) {
                        NetworkManager2.getDetailsGame(gameID)
                    }

                    val nameGame = view.findViewById<TextView>(R.id.textView2)
                    val descriptionGame = view.findViewById<TextView>(R.id.textView3)

                    nameGame.text = gameDetailFirst.gameDetail.data.name
                    descriptionGame.text = gameDetailFirst.gameDetail.data.shortDescription

                    val gameBackground = view.findViewById<ImageView>(R.id.imageView3)
                    val gameCovert = view.findViewById<ImageView>(R.id.imageView4)
                    Glide.with(fragment).load(gameDetailFirst.gameDetail.data.backgroundRaw).into(gameBackground)
                    Glide.with(fragment).load(gameDetailFirst.gameDetail.data.headerImage).into(gameCovert)

                    val adapter = GameListAdapter(response.response.ranks)
                    gameReviewView.layoutManager = LinearLayoutManager(context)
                    gameReviewView.adapter = adapter

                    val moreInformation: Button = view.findViewById(R.id.button)
                    moreInformation.setOnClickListener(View.OnClickListener {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, DescriptionGameActivity.DescriptionGameFragment.newInstance(gameID))
                            .addToBackStack("more_information")
                            .commitAllowingStateLoss()
                    })
                } catch (e: Exception) {

                }
            }
        }
    }
}
