package com.example.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.home.HomeScreen
import com.example.myapplication.retrofit.BaseResponse
import com.example.myapplication.retrofit.SessionManager


class MainActivity :  AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var sessionManager: SessionManager

    class ToBeCalled {
        val MY_PREFS_NAME = "MyPrefsFile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sessionManager = SessionManager(this)

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    sessionManager.saveAuthToken(it.data!!.record.authtoken)

                    val intent = Intent(this@MainActivity, HomeScreen::class.java)
                    intent.putExtra("authtoken", it.data!!.record.authtoken)
                    intent.putExtra("firstName", it.data!!.record.firstName)
                    intent.putExtra("lastName", it.data!!.record.lastName)
                    intent.putExtra("profileImg", it.data!!.record.profileImg)
                    startActivity(intent)

                }


                is BaseResponse.Error -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            if (validate()){
                doLogin()
            }
        }


    }

    private fun navigateToHome() {

    }
    private fun validate() :Boolean {
        if (binding.txtInputEmail.text.toString().isEmpty()) {
            binding.txtInputEmail.error = "Please Enter E-mail Address"
            return false
        } else if (binding.txtPass.text.toString().isEmpty()) {
            binding.txtPass.error = "Please Enter Your Password"
            return false
        }
        return true
    }
    fun doLogin() {
        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }



    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}
