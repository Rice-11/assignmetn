package com.example.piggyfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.example.piggyfinal.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btRegister.setOnClickListener {

            startActivity(Intent(this@Login, Register::class.java))


                val Mainpage = findViewById<Button>(R.id.rbutton)
                Mainpage.setOnClickListener {
                    when {
                        TextUtils.isEmpty(binding.lgmail.text.toString().trim { it <= ' ' }) -> {
                            Toast.makeText(
                                this@Login,
                                "Please enter your Gmail",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        TextUtils.isEmpty(binding.lpassword.text.toString().trim { it <= ' ' }) -> {
                            Toast.makeText(
                                this@Login,
                                "Please enter your Passowrd",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            val email: String = binding.lgmail.text.toString().trim { it <= ' ' }
                            val password: String = binding.lpassword.text.toString().trim { it <= ' ' }

                            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(
                                    OnCompleteListener<AuthResult> { task ->

                                        if (task.isSuccessful) {

                                            val firebaseUser: FirebaseUser = task.result!!.user!!

                                            Toast.makeText(
                                                this@Login,
                                                "You are logged in Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()



                                            val intent =
                                                Intent(this@Login, budget::class.java)
                                            intent.flags =
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            intent.putExtra(
                                                "user_id",
                                                FirebaseAuth.getInstance().currentUser!!.uid
                                            )
                                            intent.putExtra("email_id", email)
                                            startActivity(intent)
                                            finish()

                                        } else {
                                            Toast.makeText(
                                                this@Login,
                                                task.exception!!.message.toString(),
                                                Toast.LENGTH_SHORT
                                            ).show()


                                        }
                                    })

                        }
                    }
                }
            }
        }
    }
