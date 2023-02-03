package com.example.gameapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameapp.NetworkManager1
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.Rank
import com.example.gameapp.adapter.GameListAdapter
import com.example.gameapp.adapter.ReviewGameAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avis_du_jeu)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ReviewGameFragment())
            .addToBackStack("review_game")
            .commit()
    }

    class ReviewGameFragment: Fragment(){
        companion object {
            fun newInstance(gameID: String?) : ReviewGameFragment {
                val fragment = ReviewGameFragment()
                val args = bundleOf("id" to gameID)
                fragment.arguments = args
                return fragment
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.review_game_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val gameID = arguments?.getString("id")
            val fragment = this

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val gameReviewView = view.findViewById<View>(R.id.recycler_view) as RecyclerView

                    val response = withContext(Dispatchers.IO) {
                        NetworkManager2.getReviewGame(gameID.toString())
                    }

                    val response2 = withContext(Dispatchers.IO) {
                        NetworkManager2.getDetailsGame(gameID.toString())
                    }
                    val backgroundImage = view.findViewById<ImageView>(R.id.imageView4)
                    val covertImage = view.findViewById<ImageView>(R.id.imageView6)
                    Glide.with(fragment).load(response2.gameDetail.data.backgroundRaw).into(backgroundImage);
                    Glide.with(fragment).load(response2.gameDetail.data.headerImage).into(covertImage);

                    val adapter = ReviewGameAdapter(response.reviews)
                    gameReviewView.layoutManager = LinearLayoutManager(context)
                    gameReviewView.adapter = adapter
                } catch (e: Exception) {
                    Log.e("Err", e.toString())
                }
            }

            val goBackButton = view.findViewById<ImageView>(R.id.imageView)
            goBackButton.setOnClickListener(View.OnClickListener {
                if (parentFragmentManager.backStackEntryCount > 0) {
                    parentFragmentManager.popBackStack();
                } else {
                    activity?.onBackPressed();
                }
            })

            val goToDescriptionButton = view.findViewById<TextView>(R.id.textView4)
            goToDescriptionButton.setOnClickListener(View.OnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DescriptionGameActivity.DescriptionGameFragment.newInstance(gameID))
                    .addToBackStack("description_game")
                    .commitAllowingStateLoss()
            })

            val likeGameList = view.findViewById<ImageView>(R.id.imageView2)
            likeGameList.setOnClickListener(View.OnClickListener {
                // liké le jeu
            })


            val wishlistGameList = view.findViewById<ImageView>(R.id.imageView3)
            wishlistGameList.setOnClickListener(View.OnClickListener {
                // ajouté wishlist
            })

        }
    }
}
