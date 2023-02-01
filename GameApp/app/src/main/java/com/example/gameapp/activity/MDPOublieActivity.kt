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
import com.example.gameapp.NetworkManager2
import com.example.gameapp.R
import com.example.gameapp.adapter.ReviewGameAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MDPOublieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mot_de_passe_oublie)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MDPOublieFragment())
            .addToBackStack("mdp_oublie")
            .commit()
    }

    class MDPOublieFragment: Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.mdp_oublie_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        }
    }
}