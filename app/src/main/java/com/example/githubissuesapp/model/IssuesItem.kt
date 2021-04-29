package com.example.githubissuesapp.model

import com.google.gson.annotations.SerializedName

data class IssuesItem(
    val active_lock_reason: Any,
    val assignee: Assignee,
    val assignees: List<Any>,
    val author_association: String,
    @SerializedName("body")
    val body: String,
    val closed_at: Any,
    val comments: Int,
    val comments_url: String,
    @SerializedName("created_at")
    val createdAt: String,
    val events_url: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val labels: List<Any>,
    val labels_url: String,
    val locked: Boolean,
    val milestone: Any,
    val node_id: String,
    val number: Int,
    val performed_via_github_app: Any,
    val pull_request: PullRequest,
    val repository_url: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    val updated_at: String,
    val url: String,
    @SerializedName("user")
    val user: User
)