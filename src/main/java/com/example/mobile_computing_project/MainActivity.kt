package com.example.mobile_computing_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        registerButton.setOnClickListener {

           performRegistration()
        }


        Haveaccount_register.setOnClickListener{
            Log.d("MainActivity", "showing Login activity")
            val intent = Intent(this, LoginActivity::class.java  )
            startActivity(intent)
        }

    }

    private fun performRegistration(){
        val email = email_editText_register.text.toString()
        val Password = password_Edittext_register.text.toString()

        if (email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this,"Please enter text in email or password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("MainActivity","email is:"+email)
        Log.d("MainActivity","Password is: $Password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,Password)
                .addOnCompleteListener{
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("Main", "Sucessfully created user with uid:${it.result?.user?.uid}")
                    saveUserToDatabase()
                }
                .addOnFailureListener{
                    Log.d("main", "Failed to Create user${it.message}")
                    Toast.makeText(this,"Failed to Create user", Toast.LENGTH_SHORT).show()
                }


    }

    private fun saveUserToDatabase (){
        Log.d("Main", "inside save user")
        val uid = FirebaseAuth.getInstance().uid.toString()
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")
        Log.d("Main", "ref value : $ref")
        val user = User(uid, username_editText_register.text.toString())
        Log.d("Main", "user value : $user")
        ref.setValue(user).addOnSuccessListener {
            Log.d("RegisterActivity", "saved user to firebase")

            val intent = Intent(this, LatestMessagesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to set value to database")

            }

    }


}