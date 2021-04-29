package com.example.githubissuesapp.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("avatar_url")
    val avatarUrl: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val avatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)