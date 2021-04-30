package com.example.githubissuesapp.repository

import com.example.githubissuesapp.api.ApiService
import com.example.githubissuesapp.model.Issues
import com.example.githubissuesapp.model.IssuesItem
import io.reactivex.Observable

class IssuesRepository {

    fun getIssues(): Observable<Issues> {

        return ApiService.api.getIssues()

    }

    fun getIssueDetail(number: Int): Observable<IssuesItem>{

        return ApiService.api.getIssueDetail("JetBrains","kotlin", number)
    }
}