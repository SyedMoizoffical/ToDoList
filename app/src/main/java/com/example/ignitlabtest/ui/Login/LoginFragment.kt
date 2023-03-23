package com.example.ignitlabtest.ui.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.ignitlabtest.R
import com.example.ignitlabtest.roomDatabase.AppDatabase
import com.example.ignitlabtest.ui.UserList.UserListModel


import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match

class LoginFragment : Fragment() {

    var loginViewModel=LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Observe Data for local database
        Observe()

        //loging Button
        btn_login.setOnClickListener {
            if (username.text.toString() == "" || password.text.toString() == "") {
                Toast.makeText(requireContext(), "Enter User Name password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loginViewModel.getUser(username.text.toString(), password.text.toString(),requireContext())

            }


        }
        //SignUp Button
        txt_signup.setOnClickListener {
            findNavController(requireParentFragment()).navigate(R.id.singUpFragment)

        }
    }



    private fun Observe() {

        loginViewModel.inquiryLiveData.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {

                if (it.isNotEmpty()) {
                    findNavController(requireParentFragment()).navigate(R.id.toDoListFragment)

                } else {
                    Toast.makeText(requireContext(), "No User Found", Toast.LENGTH_SHORT).show()
                }
            })
    }
}