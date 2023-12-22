package com.example.piggyfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.piggyfinal.databinding.ActivityBudgetBinding
import com.example.piggyfinal.databinding.ActivityLoginBinding

class budget : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.helpbtn.setOnClickListener {

            startActivity(Intent(this@budget,help::class.java))
        }
    }
}