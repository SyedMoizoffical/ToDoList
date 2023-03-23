package com.example.ignitlabtest.roomDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ignitlabtest.ui.UserList.UserListModel


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFormInsertModel(insertUserFormInsertModel: UserListModel)

    @Query("SELECT * FROM UserRegistration ")
    fun getAllUser(): List<UserListModel>

    @Query("Delete  FROM UserRegistration Where id=:userId")
    fun delete(userId: Int)

    @Query("SELECT * FROM UserRegistration where UserName=:username and UserPassword=:password")
    fun checkuserstatus(username: String, password: String): List<UserListModel>

    @Query("SELECT * FROM UserRegistration Where id=:userId")
    fun getAllUserProfile(userId: Int): List<UserListModel>

    @Query("update UserRegistration set UserFirstName=:vUserFirstName ,UserLastName=:vUserLastName ,UserName=:vUserName,UserEmailAddress=:vUserEmailAddress where id=:userId")
    fun updateUserProfile(
        userId: Int,
        vUserFirstName: String,
        vUserLastName: String,
        vUserName: String,
        vUserEmailAddress: String
    )
}