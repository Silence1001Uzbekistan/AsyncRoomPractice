package com.example.asyncroompractice.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asyncroompractice.Entity.User
import com.example.asyncroompractice.databinding.ItemUserBinding

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: User) {

            itemUserBinding.tv1.text = user.username
            itemUserBinding.tv2.text = user.password

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {

        return list.size

    }

}