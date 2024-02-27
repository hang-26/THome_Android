package com.example.thome.start.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.thome.R
import com.example.thome.databinding.ActivityLoginBinding
import com.example.thome.start.data.DataClass
import com.example.thome.start.data.user.UserData
import com.example.thome.start.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {

        }
    }

    var userType: Int = 0
    lateinit var dataHelper: DataClass
    lateinit var user: UserData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataHelper = DataClass(this)

        Log.d(javaClass.simpleName, "onCreate: $userType")
        setEventOnClick()
    }
    fun setEventOnClick() {
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivityForResult.launch(intent)
        }

        binding.btLogin.setOnClickListener {
            handlerLogin()
        }
    }

    fun setUserType() {

        if (binding.rbRenter.isChecked) {
            userType = 0
        }
        else if (binding.rbDomesticWorker.isChecked) {
            userType = 1
        }
    }

    fun handlerLogin() {
        setUserType()
        var userName = binding.etUserName.text.toString()
        var passUser = binding.etPassWork.text.toString()

        Log.d(javaClass.simpleName, "setEventOnClick: $userName")
        Log.d(javaClass.simpleName, "setEventOnClick: $passUser")
        Log.d(javaClass.simpleName, "setEventOnClick: $userType")

        if (userName.isNotEmpty() && passUser.isNotEmpty()) {
            var check = dataHelper.checkUser(userType, userName, passUser)
            Log.d(javaClass.simpleName, "setEventOnClick: $check ")
            if (check == true) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                var intentHome = Intent(this, HomeActivity::class.java)
                startActivityForResult.launch(intentHome)
            }
        }
    }

}