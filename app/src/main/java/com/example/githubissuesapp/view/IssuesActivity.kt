package com.example.githubissuesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubissuesapp.databinding.ActivityIssuesBinding

class IssuesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssuesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssuesBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }



    }

}