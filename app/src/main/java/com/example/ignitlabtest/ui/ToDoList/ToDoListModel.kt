package com.example.ignitlabtest.ui.ToDoList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoItem")
data class ToDoListModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Description: String,
    val Status: Int,

    ) {

}
