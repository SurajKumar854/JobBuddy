package com.sampple.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: AppCompatButton
    private lateinit var fireAuth: FirebaseAuth
    private lateinit var register: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializingValues()

    }

    fun initializingValues() {
        fireAuth = Firebase.auth
        email = findViewById(R.id.email)
        password = findViewById(R.id.pass)
        login = findViewById(R.id.loginBtn)
        register = findViewById(R.id.register)
        register.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
        login.setOnClickListener() {
            loginwithID(email.text.toString().trim(), password.text.toString().trim())
        }
    }

    fun loginwithID(ee: String, pass: String) {

        fireAuth = FirebaseAuth.getInstance()
        fireAuth.signInWithEmailAndPassword(ee, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Homeactivity::class.java)
                startActivity(intent)
                finish()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
}