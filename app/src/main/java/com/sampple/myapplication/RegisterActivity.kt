package com.sampple.myapplication

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class RegisterActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var emailEdt: EditText
    private lateinit var passEdt: EditText
    private lateinit var passEdt2: EditText
    private lateinit var registerBtn: Button

    private lateinit var fireAuth: FirebaseAuth
    private lateinit var progress: ProgressBar
    private  lateinit var  loginText:TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // finding the button
        fireAuth = Firebase.auth
        registerBtn = findViewById<Button>(R.id.registerbtn)

        loginText=findViewById(R.id.loginBtn)
        emailEdt = findViewById<EditText>(R.id.email)
        passEdt = findViewById<EditText>(R.id.password1)

        passEdt2 = findViewById<EditText>(R.id.password1)
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loading ...")
        loginText.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



        }
        registerBtn.setOnClickListener {

            // Getting the user input
            progressDialog.show();
            val email = emailEdt.text.toString()
            val pass = passEdt.text.toString()
            val pass2 = passEdt2.text.toString()
            if (email.isEmpty() || pass2.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill email password properly", Toast.LENGTH_SHORT)
                    .show()


            }


            if (!pass.equals(pass2)) {
                Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show()
            }
            if (email.isNotEmpty() && pass.equals(pass2) && pass.isNotEmpty() && pass2.isNotEmpty()) {

                fireAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) {


                        if (it.isSuccessful) {

                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                            progressDialog.dismiss()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            progressDialog.dismiss()
                            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
                        }
                    }




            }

        }


    }


}