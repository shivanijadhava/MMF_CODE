package com.example.myapplication.login

data class LoginResponse(
    val message: String,
    val record: Record,
    val status: Boolean
)

data class Record(
    val authtoken: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val phoneNo: String,
    val profileImg: String
)