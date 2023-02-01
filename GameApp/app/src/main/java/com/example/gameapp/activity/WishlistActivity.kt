package com.example.gameapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

class WishlistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wishlist)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WishlistFragment())
            .addToBackStack("wishlist")
            .commit()
    }

    class WishlistFragment: Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.wishlist_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val gameReviewView = view.findViewById<View>(R.id.recycler_view_wishlist) as RecyclerView

                    val response = withContext(Dispatchers.IO) {
                        NetworkManager1.getMostPlayedGames()
                    }
                    val adapter = GameListAdapter(response.response.ranks)
                    gameReviewView.layoutManager = LinearLayoutManager(context)
                    gameReviewView.adapter = adapter
                } catch (e: Exception) {

                }
            }

            val closeList = view.findViewById<ImageView>(R.id.imageView)
            closeList.setOnClickListener(View.OnClickListener {
                if (parentFragmentManager.backStackEntryCount > 0) {
                    parentFragmentManager.popBackStack();
                } else {
                    activity?.onBackPressed();
                }
            })
        }
    }
}