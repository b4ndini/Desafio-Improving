package com.example.githubissuesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubissuesapp.R
import com.example.githubissuesapp.databinding.ActivityIssueDetailBinding
import com.example.githubissuesapp.viewModel.IssueDetailViewModel

class IssueDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssueDetailBinding
    private lateinit var viewModel: IssueDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val issueNumber = intent.getIntExtra("issue_number", 0)
        viewModel = ViewModelProvider(this).get(IssueDetailViewModel::class.java)


        observes()
        viewModel.getIssueDetail(issueNumber)

    }

    private fun observes() {
        viewModel.issueDetailLiveData.observe(this, {
            it?.let{

                var date = it.createdAt.replace("-", "/", true)
                binding.tvIssueTitle.text = it.title
                binding.tvDescription.text = "Description:\n\n" + it.body
                binding.tvDate.text = "At " + date.substring(0, 10)
                Glide.with(this).load(it.user.avatarUrl).placeholder(R.drawable.ic_launcher_background).into(binding.ivOwnerImage);


            }
        })

        viewModel.errorMsgLiveData.observe(this,{
            it?.let{
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
            }
        })
    }


}