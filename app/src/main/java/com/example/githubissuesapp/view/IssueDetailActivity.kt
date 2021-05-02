package com.example.githubissuesapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubissuesapp.databinding.ActivityIssueDetailBinding
import com.example.githubissuesapp.utils.formatDateToPtBr
import com.example.githubissuesapp.viewModel.IssueDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssueDetailBinding
    private val viewModel: IssueDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val issueNumber = intent.getIntExtra("issue_number", 0)
        //viewModel = ViewModelProvider(this).get(IssueDetailViewModel::class.java)


        observes()
        viewModel.getIssueDetail(issueNumber)

    }

    private fun observes() {
        viewModel.issueDetailLiveData.observe(this, {
            it?.let{


                binding.tvIssueTitle.text = it.title
                if(it.body.isNullOrEmpty()){
                    val empty = "Description:\n\nNo description provided."
                    binding.tvDescription.text = empty
                }else {
                    val text = "Description: \n\n${it.body}"
                    binding.tvDescription.text = text
                }


                binding.tvDate.text = it.createdAt.formatDateToPtBr()

                Glide.with(this)
                    .load(it.user.avatarUrl)
                    .into(binding.ivOwnerImage)

                val issue = it
                binding.btnLink.setOnClickListener{
                    val url: String = issue.htmlUrl
                    val intent = Intent(Intent.ACTION_VIEW);
                    intent.data = Uri.parse(url);
                    startActivity(intent)
                }



            }
        })

        viewModel.errorMsgLiveData.observe(this,{
            it?.let{
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
            }
        })
    }


}