package com.example.myapplication.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityHomeScreenBinding
import com.example.myapplication.retrofit.BaseResponse


class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private val viewModel by viewModels<HomeViewModel>()
    var page = 0
    var limit:kotlin.Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        val intent = intent
        val authtoken = intent.getStringExtra("authtoken")
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val profileImg = intent.getStringExtra("profileImg")
        binding.fName.text=firstName
        binding.lName.text=lastName

        Glide.with(this)
            .load(profileImg)
            .into(binding.image)

        setContentView(view)
        viewModel.userList(authtoken)

        binding.recyclerview.visibility=View.VISIBLE
        binding.recyclerviewGrid.visibility=View.GONE

        binding.listView.setOnClickListener {
            binding.recyclerview.visibility=View.VISIBLE
            binding.recyclerviewGrid.visibility=View.GONE
        }
        binding.gridView.setOnClickListener {
            binding.recyclerview.visibility=View.GONE
            binding.recyclerviewGrid.visibility=View.VISIBLE
        }
        //not working yet
        binding.idNestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                page++
                viewModel.userList(authtoken)
            }
        })

        viewModel.userResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    var userListModel= it.data?.userList

                    userListModel?.let { it1 -> setAdapter(it.data!!.userList) }
                    userListModel?.let { it1 -> setAdapterGrid(it.data!!.userList) }
                }

                is BaseResponse.Error -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    stopLoading()
                }
            }
        }
    }
    fun setAdapterGrid(userListModel: ArrayList<User>){
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerviewGrid.layoutManager = layoutManager
        val adapter = UserGridAdapter(this,userListModel)
        binding.recyclerviewGrid.adapter = adapter
    }
    fun setAdapter(userListModel: ArrayList<User>){
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter =
            UserAdapter(this,userListModel)
        binding.recyclerview.adapter= adapter
    }
    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }
}