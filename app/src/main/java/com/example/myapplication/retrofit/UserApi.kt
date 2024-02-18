package com.example.myapplication.retrofit

import com.example.myapplication.home.UserListModel
import com.example.myapplication.login.LoginRequest
import com.example.myapplication.login.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

    @POST("api/userLogin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>


    @GET("api/userList")
  suspend  fun getUserList(
        @Header("Authorization") authtoken: String
    ): Response<UserListModel>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}