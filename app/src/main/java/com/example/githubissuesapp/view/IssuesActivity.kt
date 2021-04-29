package com.example.githubissuesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubissuesapp.databinding.ActivityIssuesBinding
import com.example.githubissuesapp.view.adapter.IssuesAdapter
import com.example.githubissuesapp.viewModel.IssuesViewModel

class IssuesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssuesBinding
    private lateinit var viewModel: IssuesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssuesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(IssuesViewModel::class.java)

        observes()
        viewModel.getIssues()

    }

    private fun observes() {

        viewModel.issuesLiveData.observe(this, {
            it?.let{
                binding.tvErrorMsg.visibility = View.GONE
                binding.rvIssuesList.apply {
                    layoutManager = LinearLayoutManager(this@IssuesActivity)
                    adapter = IssuesAdapter(it)

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

/*    private fun updateUI(issues: Issues) {
        binding.rvIssuesList.apply {
            layoutManager = LinearLayoutManager(this@IssuesActivity)
            adapter = IssuesAdapter(issues)

        }

    }*/


}