package com.example.myapplication.home

data class UserListModel(
    val currentPage: Int,
    val lastPage: Int,
    val message: String,
    val perPage: Int,
    val status: Boolean,
    val total: Int,
    val userList: ArrayList<User>
)