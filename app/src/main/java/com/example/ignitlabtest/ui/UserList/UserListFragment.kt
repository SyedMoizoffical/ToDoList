package com.example.ignitlabtest.ui.UserList

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ignitlabtest.R
import com.example.ignitlabtest.roomDatabase.AppDatabase
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.fragment_user_list.RecyclerViewUserList


class UserListFragment : Fragment(), UserDelete {
    lateinit var userAdapter: UserListAdapter
    private var db: AppDatabase? = null
    lateinit var userlist: ArrayList<UserListModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userlist = ArrayList()
        //Get All USer Data
        getAllUserFromLocalDataBase()
        //Back to TODOList
        back_userlist.setOnClickListener {
            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.toDoListFragment)

        }
    }

    private fun loadAdapter(list: ArrayList<UserListModel>) {
        RecyclerViewUserList.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        userAdapter = UserListAdapter(list, this)
        RecyclerViewUserList.adapter = userAdapter
    }

    fun getAllUserFromLocalDataBase() {
        userlist.clear()

        db = AppDatabase.getAppDataBase(requireContext())

        db?.userdao()?.getAllUser()!!.forEach()
        {
            userlist.add(it)


        }
        loadAdapter(userlist)

    }

    override fun onDeleteClick(position: Int) {
        deleteuser(position)
        getAllUserFromLocalDataBase()
    }

    override fun onEditClick(position: Int) {
        showAlertDialog(position)
    }

    override fun onItemUpdateClick(position: Int) {
        val id = userlist.get(position).id
        val bundle = bundleOf("Userid" to id)
        NavHostFragment.findNavController(requireParentFragment())
            .navigate(R.id.profileFragment, bundle)

    }

    fun deleteuser(position: Int) {

        userlist.get(position).id
        if (db != null) {
            db = AppDatabase.getAppDataBase(requireContext())
            db?.userdao()?.delete(userlist.get(position).id)
        }


    }
    //Open Dialog Box To Edit Current user
    private fun showAlertDialog(position: Int) {
        val dialogBuilder =
            context?.let { AlertDialog.Builder(it) }
        val layoutView: View =
            layoutInflater.inflate(R.layout.editdialog, null)
        val dialogButton =
            layoutView.findViewById<Button>(R.id.btnDialog)

        dialogBuilder?.setView(layoutView)
        val alertDialog = dialogBuilder?.create()
        alertDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        alertDialog?.show()

        val txtfirstName =
            layoutView.findViewById<EditText>(R.id.txt_firstName)

        val txtlastname =
            layoutView.findViewById<EditText>(R.id.txt_lastname)

        val txtUserNmae =
            layoutView.findViewById<TextView>(R.id.txt_userName)

        val txt_mail =
            layoutView.findViewById<TextView>(R.id.txt_email)


        txtfirstName.setText(userlist.get(position).UserFirstName.toString())
        txtlastname.setText(userlist.get(position).UserLastName.toString())
        txtUserNmae.setText(userlist.get(position).UserName.toString())
        txt_mail.setText(userlist.get(position).UserEmailAddress.toString())
        val userid = userlist.get(position).id

        dialogButton.setOnClickListener {

            db = AppDatabase.getAppDataBase(
                requireContext()
            )

            db?.userdao()?.updateUserProfile(
                userid,
                txtfirstName.text.toString(),
                txtlastname.text.toString(),
                txtUserNmae.text.toString(),
                txt_mail.text.toString(),
            )

            alertDialog?.dismiss()
            getAllUserFromLocalDataBase()

        }


    }

}