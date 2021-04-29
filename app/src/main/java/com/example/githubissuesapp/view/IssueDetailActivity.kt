package com.example.githubissuesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubissuesapp.databinding.ActivityIssueDetailBinding

class IssueDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssueDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}