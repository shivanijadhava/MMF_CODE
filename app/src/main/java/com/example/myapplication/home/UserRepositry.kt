package com.example.myapplication.home

import android.util.Log
import com.example.myapplication.retrofit.UserApi
import retrofit2.Response

class UserRepositry {
    suspend fun Userlist(authToken: String?): Response<UserListModel>? {
        return  UserApi.getApi()?.getUserList(authToken!!)
    }}