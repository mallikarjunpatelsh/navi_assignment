package com.example.naviassignment.model

data class PullRequest(
    var title: String,
    var created_at: String,
    var closed_at: String,
    var user: User
) {
}