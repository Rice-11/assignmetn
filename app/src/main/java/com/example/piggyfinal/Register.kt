package com.example.piggyfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCanceledListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.view.View.OnClickListener
import com.example.piggy.MainActivity
import com.example.piggyfinal.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener



class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val Mainpage = findViewById<Button>(R.id.rbutton)
            Mainpage.setOnClickListener {
                when {
                    TextUtils.isEmpty(binding.rgmail.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@Register,
                            "Please enter your Gmail",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    TextUtils.isEmpty(binding.rpassword.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@Register,
                            "Please enter your Passowrd",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        val email: String = binding.rgmail.text.toString().trim { it <= ' ' }
                        val password: String = binding.rpassword.text.toString().trim { it <= ' ' }

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                OnCompleteListener<AuthResult> { task ->

                                    if (task.isSuccessful) {

                                        val firebaseUser: FirebaseUser = task.result!!.user!!

                                        Toast.makeText(
                                            this@Register,
                                            "The Registerastion is sucessful",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        val intent =
                                            Intent(this@Register, Login::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        intent.putExtra("user_id", firebaseUser.uid)
                                        intent.putExtra("email_id", email)
                                        startActivity(intent)
                                        finish()

                                    } else {
                                        Toast.makeText(
                                            this@Register,
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

