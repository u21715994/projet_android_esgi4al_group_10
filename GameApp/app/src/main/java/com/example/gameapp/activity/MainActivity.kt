package com.example.gameapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.gameapp.R
import com.example.gameapp.backend.UserAuth
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

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
            val button = view.findViewById<Button>(R.id.button)
            button.setOnClickListener {
                val email = view.findViewById<EditText>(R.id.editText).text.toString()
                val password = view.findViewById<EditText>(R.id.editText2).text.toString()
                val auth = UserAuth()
                auth.signInUser(email, password, requireContext(), parentFragmentManager)
            }


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
