package com.example.ignitlabtest.ui.SignUp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.ignitlabtest.R
import com.example.ignitlabtest.roomDatabase.AppDatabase
import com.example.ignitlabtest.roomDatabase.UserDao
import com.example.ignitlabtest.ui.UserList.UserListModel
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SingUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var db: AppDatabase? = null
    private var UserDao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_signup.setOnClickListener {
            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.loginFragment)

        }
        //Save User data in ROom DataBase
        btn_submit_signup.setOnClickListener {
            val userdate: UserListModel = UserListModel(
                0,
                txt_UserFirtName_signUp.text.toString(),
                txt_UserLatName_signUp.text.toString(),
                txt_UserName_signUp.text.toString(),
                txt_UserEmail_signUp.text.toString(),
                txt_Userassword_signUp.text.toString(),
            )
            storeInRoomDb(userdate)
        }
    }

    fun storeInRoomDb(User: UserListModel) {
        db = AppDatabase.getAppDataBase(requireContext())
        UserDao = db?.userdao()
        with(UserDao) {
            this?.insertUserFormInsertModel(User)
        }
        Clear()

    }

    fun Clear() {
        txt_UserName_signUp.setText("")
        txt_UserFirtName_signUp.setText("")
        txt_UserLatName_signUp.setText("")
        txt_UserEmail_signUp.setText("")
        txt_Userassword_signUp.setText("")
        Toast.makeText(requireContext(), "User SSuccessfully Created", Toast.LENGTH_SHORT).show()
        NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.loginFragment)
    }


}