package com.example.ignitlabtest.ui.ToDoList

interface ToDoListItemDelete {
    fun onDeleteClick(position : Int)
    fun onEditClick(position : Int)
    fun onItemUpdateClick(position : Int)
    fun onItemDoneClick(position : Int,status:Int)

}