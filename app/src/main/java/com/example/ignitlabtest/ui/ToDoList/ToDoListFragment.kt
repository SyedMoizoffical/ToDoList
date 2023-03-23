package com.example.ignitlabtest.ui.ToDoList

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ignitlabtest.R
import com.example.ignitlabtest.roomDatabase.AppDatabase
import com.example.ignitlabtest.roomDatabase.ToDoDao
import kotlinx.android.synthetic.main.fragment_to_do_list.*


class ToDoListFragment : Fragment(), ToDoListItemDelete {
    lateinit var todolistAdapter: ToDoListAdapter
    private var db: AppDatabase? = null
    private var toDoDao: ToDoDao? = null
    lateinit var todolist: ArrayList<ToDoListModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todolist = ArrayList()
        getAllItemLocalDataBase()
// Logout
        back_todolist.setOnClickListener {
            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.loginFragment)
//User List
        }
        back_userprofile.setOnClickListener {
            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.userListFragment)
//Add new item in todoList
        }
        btnAddToDoItem.setOnClickListener {
            showAlertDialog_AddItem()
        }
    }

    private fun loadAdapter(list: ArrayList<ToDoListModel>) {
        RecyclerViewToDOList.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        todolistAdapter = ToDoListAdapter(list, this)
        RecyclerViewToDOList.adapter = todolistAdapter
    }
//GEt All DoTo List In Room Database
    fun getAllItemLocalDataBase() {
        todolist.clear()

        db = AppDatabase.getAppDataBase(requireContext())

        db?.toDoDao()?.getAllItem()!!.forEach()
        {
            todolist.add(it)


        }
        loadAdapter(todolist)

    }
    //Delete Item In Room Database

    override fun onDeleteClick(position: Int) {
        deleteTodoItem(position)
        getAllItemLocalDataBase()
    }

    override fun onEditClick(position: Int) {
        showAlertDialog_EditItem(position)
    }

    override fun onItemUpdateClick(position: Int) {
        val id = todolist.get(position).id
        val bundle = bundleOf("Userid" to id)
        NavHostFragment.findNavController(requireParentFragment())
            .navigate(R.id.profileFragment, bundle)

    }

    override fun onItemDoneClick(position: Int, status: Int) {
        val id = todolist.get(position).id
        db = AppDatabase.getAppDataBase(
            requireContext()
        )

        db?.toDoDao()?.updateStatus(
            id,
            status
        )
    }

    fun deleteTodoItem(position: Int) {

        todolist.get(position).id
        if (db != null) {
            db = AppDatabase.getAppDataBase(requireContext())
            db?.toDoDao()?.delete(todolist.get(position).id)
        }


    }
//Open Dialog Box To Edit Current item
    @SuppressLint("MissingInflatedId")
    private fun showAlertDialog_EditItem(position: Int) {
        val dialogBuilder =
            context?.let { AlertDialog.Builder(it) }
        val layoutView: View =
            layoutInflater.inflate(R.layout.editdialog_todolist, null)
        val dialogButton =
            layoutView.findViewById<Button>(R.id.btnDialog_todolist)

        dialogBuilder?.setView(layoutView)
        val alertDialog = dialogBuilder?.create()
        alertDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        alertDialog?.show()

        val txtDescription =
            layoutView.findViewById<EditText>(R.id.txt_Description_edit)
        txtDescription.setText(todolist.get(position).Description.toString())

        val userid = todolist.get(position).id

        dialogButton.setOnClickListener {

            db = AppDatabase.getAppDataBase(
                requireContext()
            )

            db?.toDoDao()?.updateDescription(
                userid,
                txtDescription.text.toString()

            )






            alertDialog?.dismiss()
            getAllItemLocalDataBase()

        }


    }
    //Open Dialog Box To Add new item
    @SuppressLint("MissingInflatedId")
    private fun showAlertDialog_AddItem() {
        val dialogBuilder =
            context?.let { AlertDialog.Builder(it) }
        val layoutView: View =
            layoutInflater.inflate(R.layout.editdialog_todolist, null)
        val dialogButton =
            layoutView.findViewById<Button>(R.id.btnDialog_todolist)

        dialogBuilder?.setView(layoutView)
        val alertDialog = dialogBuilder?.create()
        alertDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        alertDialog?.show()

        val txtDescription =
            layoutView.findViewById<EditText>(R.id.txt_Description_edit)

        dialogButton.setOnClickListener {
            val toDoListModel: ToDoListModel = ToDoListModel(
                0,
                txtDescription.text.toString(),
                0,

                )
            storeInRoomDb(toDoListModel)
            alertDialog?.dismiss()
            getAllItemLocalDataBase()
        }


    }

    fun storeInRoomDb(User: ToDoListModel) {
        db = AppDatabase.getAppDataBase(requireContext())
        toDoDao = db?.toDoDao()
        with(toDoDao) {
            this?.insertToDoItemInsertModel(User)
        }


    }
}