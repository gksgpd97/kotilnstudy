package com.example.kotlinstudy

data class CommentData(
    var userImage: Int,
    var userId: String,
    var date: String,
    var rating: Float,
    var comment: String,
    var thumbUpCount: Int
)
