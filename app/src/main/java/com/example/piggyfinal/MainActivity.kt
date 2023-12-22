package com.example.piggy

import android.content.Intent
import com.example.piggyfinal.Login
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.piggyfinal.databinding.ActivityMainBinding
import com.example.piggyfinal.databinding.ActivityRegisterBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        binding.usergmail.text = "Gmail :: $emailId"
        binding.userid.text = "User ID :: $userId"

        binding.logoutbtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, Login::class.java))
            finish()
        }


    }
}