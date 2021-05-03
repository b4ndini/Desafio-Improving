package com.example.githubissuesapp.model

import com.google.gson.annotations.SerializedName

data class IssuesItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: User
)