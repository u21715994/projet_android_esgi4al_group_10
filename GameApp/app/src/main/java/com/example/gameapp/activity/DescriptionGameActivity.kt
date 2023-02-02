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
import com.bumptech.glide.Glide
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DescriptionGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_du_jeu)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DescriptionGameFragment())
            .addToBackStack("description_game")
            .commit()
    }

    class DescriptionGameFragment() : Fragment(){
        companion object {
            fun newInstance(gameID: String?) : DescriptionGameFragment {
                val fragment = DescriptionGameFragment()
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
                .inflate(R.layout.description_game_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val gameID = arguments?.getString("id")
            val fragment = this

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        NetworkManager2.getDetailsGame(gameID.toString())
                    }
                    val backgroundImage = view.findViewById<ImageView>(R.id.imageView4)
                    val covertImage = view.findViewById<ImageView>(R.id.imageView6)
                    Glide.with(fragment).load(response.gameDetail.data.backgroundRaw).into(backgroundImage);
                    Glide.with(fragment).load(response.gameDetail.data.headerImage).into(covertImage);

                    val name_game = view.findViewById<TextView>(R.id.textView2)
                    name_game.text = response.gameDetail.data.name

                    val game_publisher = view.findViewById<TextView>(R.id.textView3)
                    game_publisher.text = response.gameDetail.data.publishers.toString()

                    val description = view.findViewById<TextView>(R.id.textView6)
                    description.text = response.gameDetail.data.detailedDescription
                } catch (e: Exception) {

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

            val goToReviewButton = view.findViewById<TextView>(R.id.textView5)
            goToReviewButton.setOnClickListener(View.OnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ReviewGameActivity.ReviewGameFragment.newInstance(gameID))
                    .addToBackStack("review_game")
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
