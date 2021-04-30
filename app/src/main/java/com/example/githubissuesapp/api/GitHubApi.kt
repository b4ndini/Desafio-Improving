package com.example.githubissuesapp.api

import com.example.githubissuesapp.model.Issues
import com.example.githubissuesapp.model.IssuesItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    // /repos/{owner}/{repo}/issues
    @GET("repos/{owner}/{repo}/issues")
    fun getIssues(
        @Path("owner") owner: String = "JetBrains",
        @Path("repo") repo: String = "kotlin"
    ): Observable<Issues>

    @GET("repos/{owner}/{repo}/issues/{issue_number}")
    fun getIssueDetail(
            @Path("owner") owner: String = "JetBrains",
            @Path("repo") repo: String = "kotlin",
            @Path("issue_number") issueNumber: Int
    ): Observable<IssuesItem>
}