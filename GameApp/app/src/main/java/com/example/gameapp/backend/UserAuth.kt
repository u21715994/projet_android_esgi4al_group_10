package com.example.gameapp.backend

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserAuth {
    //Variables
    private var mAuth: FirebaseAuth? = null
    private var mDatabase: DatabaseReference? = null

    //Initialisation
    init {
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
    }

    //Fonction de création de notre user
    fun createU(email: String, password: String, userData: Map<String, Any>, context: Context): Boolean {
        var result = false
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result = true
                    val user = mAuth!!.currentUser
                    storeUserData(user!!.uid, userData)
                    Toast.makeText(context, "Inscription réussie!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Inscription échouée, veuillez réessayer", Toast.LENGTH_SHORT).show()
                }
            }
        return result
    }

    //Fonction de connection de notre user
    fun signInUser(email: String, password: String, context: Context): Boolean {
        var result = false
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result = true
                    val user = mAuth!!.currentUser
                    Toast.makeText(context, "Connexion réussie!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Connexion échouée, veuillez réessayer", Toast.LENGTH_SHORT).show()
                }
            }
        return result
    }

    //Fonction pour reset le password
    fun resetPassword(email: String) {
        mAuth!!.sendPasswordResetEmail(email)
    }

    //Fonction pour stocker les infos de l'utilisateur dans la bdd
    private fun storeUserData(userId: String, userData: Map<String, Any>) {
        mDatabase!!.child("users").child(userId).setValue(userData)
    }
}
