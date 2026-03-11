package com.androidtutorials.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidtutorials.domain.model.User


class UserAdapter (private val users : List <User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UserAdapter.UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {

        val user = users[position]
        holder.itemView.findViewById<TextView>(android.R.id.text1).text=user.name
        holder.itemView.findViewById<TextView>(android.R.id.text2).text=user.email
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder (view : View): RecyclerView.ViewHolder(view)

}