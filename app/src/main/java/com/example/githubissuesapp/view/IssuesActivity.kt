package com.example.githubissuesapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubissuesapp.databinding.ActivityIssuesBinding
import com.example.githubissuesapp.view.adapter.IssuesAdapter
import com.example.githubissuesapp.viewModel.IssuesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssuesBinding
    private val viewModel: IssuesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssuesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observes()
        viewModel.getIssues()

    }

    private fun observes() {

        viewModel.issuesLiveData.observe(this, {
            it?.let{
                binding.tvErrorMsg.visibility = View.GONE
                binding.rvIssuesList.apply {
                    layoutManager = LinearLayoutManager(this@IssuesActivity)
                    adapter = IssuesAdapter(it){ position ->
                        val intent = Intent(this@IssuesActivity, IssueDetailActivity::class.java).apply {
                            putExtra("issue_number", it[position].number)
                        }
                        startActivity(intent)
                    }

                }
            }
        })

        viewModel.errorMsgLiveData.observe(this,{
            it?.let{
                binding.tvErrorMsg.text = it
                binding.tvErrorMsg.visibility = View.VISIBLE
            }
        })
    }



}