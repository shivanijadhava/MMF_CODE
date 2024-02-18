package com.example.myapplication.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.retrofit.BaseResponse
import kotlinx.coroutines.launch

class HomeViewModel (application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepositry()
    val userResult: MutableLiveData<BaseResponse<UserListModel>> = MutableLiveData()


    fun userList(authToken: String?) {
        userResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.Userlist( "Bearer " +authToken)
                if (response?.code() == 200) {
                    userResult.value = BaseResponse.Success(response.body())
                } else {
                    userResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                userResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}