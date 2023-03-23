package com.example.ignitlabtest.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ignitlabtest.ui.ToDoList.ToDoListModel


@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDoItemInsertModel(insertUserFormInsertModel: ToDoListModel)

    @Query("SELECT * FROM ToDoItem ")
    fun getAllItem(): List<ToDoListModel>

    @Query("Delete  FROM ToDoItem Where id=:userId")
    fun delete(userId: Int)


    @Query("update ToDoItem set Description=:description  where id=:userId")
    fun updateDescription(
        userId: Int,
        description: String
    )

    @Query("update ToDoItem set Status=:status  where id=:userId")
    fun updateStatus(
        userId: Int,
        status: Int
    )
}