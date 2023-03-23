package com.example.ignitlabtest.ui.UserList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserRegistration")
data class UserListModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val UserFirstName: String,
    val UserLastName: String,
    val UserName: String,
    val UserEmailAddress: String,
    val UserPassword: String,

    ) {

}
