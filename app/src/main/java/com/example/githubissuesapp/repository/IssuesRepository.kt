package com.example.githubissuesapp.repository

import com.example.githubissuesapp.api.ApiService
import com.example.githubissuesapp.model.Issues
import io.reactivex.Observable

class IssuesRepository {

    fun getIssues(query: String): Observable<Issues> {

        return ApiService.api.getIssues()

    }
}