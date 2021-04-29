package com.example.githubissuesapp.api

import com.example.githubissuesapp.model.Issues
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
}