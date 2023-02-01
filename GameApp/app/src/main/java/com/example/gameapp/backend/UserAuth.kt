package com.example.gameapp.backend

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
    fun createUser(email: String, password: String, userData: Map<String, Any>) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth!!.currentUser
                    storeUserData(user!!.uid, userData)
                }
            }
    }

    //Fonction de connection de notre user
    fun signInUser(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth!!.currentUser
                }
            }
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
