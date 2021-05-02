package com.example.githubissuesapp.di

import com.example.githubissuesapp.repository.IssuesRepository
import com.example.githubissuesapp.viewModel.IssueDetailViewModel
import com.example.githubissuesapp.viewModel.IssuesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val issueModule = module {

    single {
        IssuesRepository()
    }

    viewModel {
        IssueDetailViewModel(
            get()
        )
    }

    viewModel {
        IssuesViewModel(
            get()
        )
    }

}