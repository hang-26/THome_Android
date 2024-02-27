package com.example.thome.start.login

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.example.thome.R
import com.example.thome.databinding.ActivitySignupBinding
import com.example.thome.start.data.DataClass
import com.example.thome.start.data.UserContractClass
import com.example.thome.start.data.user.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var dataHelper: DataClass
    lateinit var user: UserData
    var useType: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataHelper = DataClass(this)
        selectRadioButton()
        setOnClickListener()
    }

    fun setOnClickListener() {



        binding.btSignup.setOnClickListener {
            Log.d("en", "setOnClickListener: " )
            val userName = binding.edtUserName.text.toString()
            val passUser = binding.edtPass.text.toString()
            val rePass = binding.edtRePass.text.toString()

            registerFireBase(userName, passUser, rePass)

        }
    }

    fun selectRadioButton() {
        if (binding.rbRender.isChecked) {
            useType = 0
        }
        else if (binding.rbDomesticWorker.isChecked) {
            useType = 1
        }

    }

    fun registerFireBase(userName: String, passUser: String, rePass: String ) {
        var auth = Firebase.auth
        if (userName.isNotEmpty() && passUser.isNotEmpty()) {
            if (passUser == rePass) {

                auth.createUserWithEmailAndPassword(userName, passUser)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(javaClass.simpleName, "createUserWithEmail:success")
                            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
//                        val user = auth.currentUser
//                        updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(javaClass.simpleName, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
//                        updateUI(null)
                        }
                    }

            } else {
                Toast.makeText(this, "Nhắc lại mật khẩu phải trùng với mật khẩu", Toast.LENGTH_SHORT).show()
            }

        } else
            Toast.makeText(this, "Tên và mật khẩu không được để trống", Toast.LENGTH_SHORT).show()
    }


    fun handelRegister(userName: String, passUser: String, rePass: String) {

        /**
         * Kiểm tra tài khoản đã tồn tại hay chưa
         */

        var check = dataHelper.checkExitUser(useType, userName)

        Log.d("tt", "setOnClickListener: $check")
        user = UserData(userName,passUser,useType )

        if (passUser.isNotEmpty() && userName.isNotEmpty()) {
            if (rePass.equals(passUser)  && check.equals(false)) {
                dataHelper.insertUser(user)
                Log.d(javaClass.simpleName, "setOnClickListener: ")
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show()
        }
    }
}