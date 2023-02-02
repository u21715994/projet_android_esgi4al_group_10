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
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class InscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inscription)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InscriptionFragment())
            .addToBackStack("inscription")
            .commit()
    }

    class InscriptionFragment: Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.inscription_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val button = view.findViewById<Button>(R.id.button)
            button.setOnClickListener {
                val email = view.findViewById<EditText>(R.id.editText).text.toString()
                val password = view.findViewById<EditText>(R.id.editText2).text.toString()
                val username = view.findViewById<EditText>(R.id.editText3).text.toString()
                val userData = mapOf("username" to username)
                val auth = UserAuth()
                if(auth.createU(email, password, userData, requireContext())){
                    //action à réaliser quand l'utilisateur est inscrit
                    Toast.makeText(requireContext(), "Vous avez déjà un compte", Toast.LENGTH_SHORT).show()
                }
                else{
                    //Action à réaliser quand l'utilisateur n'est pas inscrit
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AccueilActivity.AccueilFragment())
                        .addToBackStack("accueil")
                        .commitAllowingStateLoss()
                }
            }
        }
    }
}
