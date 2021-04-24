package com.example.mobile_computing_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Login_button.setOnClickListener{
            val email = editTextTextEmailAddress.text.toString()
            val password = password_Edittext_register.text.toString()

            Log.d("Login", "Attempt login with email/password: $email")
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
        }
    }
}