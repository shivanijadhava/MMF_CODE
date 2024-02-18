package com.example.myapplication.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.UserlistSingleItemBinding

class UserAdapter(var context: Context, val itemList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.ModelViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.userlist_single_item, parent, false)
        return ModelViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserAdapter.ModelViewHolder, position: Int) {
        holder.f_name.text=(itemList[position].first_name)
        holder.l_name.text=(itemList[position].last_name)
        holder.email.text=(itemList[position].email)
        holder.phoneNumber.text=(itemList[position].phone_no)
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var f_name: TextView
        var l_name: TextView
        var email: TextView
        var phoneNumber: TextView
        init {

            f_name = itemView.findViewById(R.id.f_name) as TextView
            l_name = itemView.findViewById(R.id.l_name) as TextView
            email = itemView.findViewById(R.id.email) as TextView
            phoneNumber = itemView.findViewById(R.id.phoneNumber) as TextView

        }

    }


}
