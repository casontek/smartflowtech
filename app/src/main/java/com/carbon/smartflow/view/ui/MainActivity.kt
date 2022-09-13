package com.carbon.smartflow.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carbon.smartflow.R
import com.carbon.smartflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //starts product list activity using intent
        Intent(
            this,
            Products::class.java
        ).apply {
            startActivity(this)
            finish()
        }
    }
}