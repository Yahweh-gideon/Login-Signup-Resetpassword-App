package com.example.myassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signuptxt: TextView
    private lateinit var loginBtn: Button

    private lateinit var resetPassword: TextView
    private lateinit var forgotPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.email)
        passwordEt = findViewById(R.id.password)

        signuptxt = findViewById(R.id.account)
        loginBtn = findViewById(R.id.login_btn)
        forgotPassword =findViewById(R.id.forgot)

        resetPassword = findViewById(R.id.forgot)

        loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@LoginActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        signuptxt.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()

        }

        resetPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}