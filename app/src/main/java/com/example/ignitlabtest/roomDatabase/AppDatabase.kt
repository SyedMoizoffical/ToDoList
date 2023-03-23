package com.example.ignitlabtest.roomDatabase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ignitlabtest.ui.ToDoList.ToDoListModel
import com.example.ignitlabtest.ui.UserList.UserListModel


@Database(
    entities = [
        UserListModel::class,
        ToDoListModel::class
    ],
    version = 1
)
abstract class AppDatabase:RoomDatabase(){
    abstract fun userdao():UserDao
    abstract fun toDoDao():ToDoDao
    companion object {
        var INSTANCE: AppDatabase? = null



        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "NewDb"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }


        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
