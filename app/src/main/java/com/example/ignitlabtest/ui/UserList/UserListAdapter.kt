package com.example.ignitlabtest.ui.UserList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ignitlabtest.databinding.UserlistviewBinding

class UserListAdapter(var arrayList: ArrayList<UserListModel>,var userdete:UserDelete) :
    Adapter<UserListAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: UserlistviewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemListFroRecycle = UserlistviewBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(itemListFroRecycle)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val arrayListSet: UserListModel = arrayList.get(position)
        holder.binding.txtUserNmae.text = arrayListSet.UserName + "" + arrayListSet.UserLastName
        holder.binding.txtUserEmail.text = arrayListSet.UserEmailAddress
        holder.binding.imgDelete.setOnClickListener{
            userdete.onDeleteClick(position)
        }
        holder.binding.imgEdit.setOnClickListener{
            userdete.onEditClick(position)
        }
        holder.itemView.setOnClickListener {
            userdete.onItemUpdateClick(position)
            return@setOnClickListener
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}