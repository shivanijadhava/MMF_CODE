package com.example.myapplication.login

import com.example.myapplication.retrofit.UserApi
import retrofit2.Response

class LoginRepositry {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}