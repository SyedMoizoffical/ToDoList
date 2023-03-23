package com.example.ignitlabtest.ui.ToDoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ignitlabtest.databinding.TodolistviewBinding


class ToDoListAdapter(var arrayList: ArrayList<ToDoListModel>, var todolistitemdelete: ToDoListItemDelete) :
    Adapter<ToDoListAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: TodolistviewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemListFroRecycle = TodolistviewBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(itemListFroRecycle)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val arrayListSet: ToDoListModel = arrayList.get(position)
        holder.binding.txtDescription.text = arrayListSet.Description
        if (arrayListSet.Status == 1){
            holder.binding.checked.isChecked=true
        }
        holder.binding.imgDelete.setOnClickListener{
            todolistitemdelete.onDeleteClick(position)
        }
        holder.binding.imgEdit.setOnClickListener{
            todolistitemdelete.onEditClick(position)
        }
        holder.itemView.setOnClickListener {
            todolistitemdelete.onItemUpdateClick(position)
            return@setOnClickListener
        }

        holder.binding.checked.setOnClickListener(View.OnClickListener {
            val isChecked: Boolean = holder.binding.checked.isChecked
            if (isChecked) {
                todolistitemdelete.onItemDoneClick(position,1)
            } else {
                todolistitemdelete.onItemDoneClick(position,0)
            }
        })
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}