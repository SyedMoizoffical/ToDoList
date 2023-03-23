package com.example.ignitlabtest.ui.Login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ignitlabtest.roomDatabase.AppDatabase
import com.example.ignitlabtest.ui.UserList.UserListModel
import kotlinx.coroutines.launch

  class LoginViewModel  () : ViewModel() {
    private var db: AppDatabase? = null
    private val _inquiryLiveData = MutableLiveData<List<UserListModel>>()
    val inquiryLiveData: LiveData<List<UserListModel>>
        get() =
            _inquiryLiveData



    fun getUser(username :String,password: String,context:Context ) {
        viewModelScope.launch {

            db = AppDatabase.getAppDataBase(context)

            db?.userdao()?.checkuserstatus(username, password)!!.forEach()
            {

                _inquiryLiveData.postValue(listOf(it))


            }
        }
    }

}