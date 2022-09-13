package com.carbon.smartflow.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carbon.smartflow.databinding.ActivityProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Products : AppCompatActivity() {
    lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}