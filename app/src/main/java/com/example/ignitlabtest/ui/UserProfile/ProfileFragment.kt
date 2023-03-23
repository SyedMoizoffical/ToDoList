package com.example.ignitlabtest.ui.UserProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.ignitlabtest.R
import com.example.ignitlabtest.roomDatabase.AppDatabase
import com.example.ignitlabtest.ui.UserList.UserListModel
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var db: AppDatabase? = null
    lateinit var userlist:ArrayList<UserListModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId=arguments?.get("Userid")
        getAllUserFromLocalDataBase(userId as Int)
        //Show User ProFile
        back_signup.setOnClickListener {
            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.toDoListFragment)

        }
    }
    fun getAllUserFromLocalDataBase(userId: Int) {

        db = AppDatabase.getAppDataBase(requireContext())

        db?.userdao()?.getAllUserProfile(userId)!!.forEach()
        {

            txt_UserName_signUp.setText(it.UserName)
            txt_UserFirtName_signUp.setText(it.UserFirstName)
            txt_UserLatName_signUp.setText(it.UserLastName)
            txt_UserEmail_signUp.setText(it.UserEmailAddress)


        }



    }

}