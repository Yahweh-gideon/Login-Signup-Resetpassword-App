package com.example.myassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var logoutBtn: Button
    private lateinit var updatePass: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        setContentView(R.layout.activity_main)

        logoutBtn = findViewById(R.id.sign_out)
        updatePass = findViewById(R.id.password_update)

        updatePass.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, UpdatePasswordActivity::class.java)
            startActivity(intent)
        }

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}