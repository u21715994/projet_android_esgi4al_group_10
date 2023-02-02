package com.example.gameapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gameapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .addToBackStack("main_activity")
            .commit()
    }

    class MainFragment: Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.activity_main_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val goToSignIn = view.findViewById<Button>(R.id.button2)
            goToSignIn.setOnClickListener(View.OnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, InscriptionActivity.InscriptionFragment())
                    .addToBackStack("inscription")
                    .commitAllowingStateLoss()
            })

            val passwordForget = view.findViewById<TextView>(R.id.textView3)
            passwordForget.setOnClickListener(View.OnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MDPOublieActivity.MDPOublieFragment())
                    .addToBackStack("mot_passe_oublie")
                    .commitAllowingStateLoss()
            })
        }
    }
}