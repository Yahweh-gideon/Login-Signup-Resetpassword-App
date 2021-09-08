package com.example.myassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class CreateAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signUpBtn: Button
    private lateinit var logintxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()

        emailEt = findViewById(R.id.email)
        passwordEt = findViewById(R.id.password)

        logintxt = findViewById(R.id.sign_in)
        signUpBtn = findViewById(R.id.sign_up_btn)

        signUpBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, CreateAccountActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        logintxt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}





