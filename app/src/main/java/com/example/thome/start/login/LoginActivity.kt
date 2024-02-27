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
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val auth = Firebase.auth
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
            loginWithFireBase()
        }
    }


    fun loginWithFireBase() {

        val intentStartActivity = Intent(this, HomeActivity::class.java)
        var userName = binding.etUserName.text.toString()
        var pass = binding.etPassWork.text.toString()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Log.d(javaClass.simpleName, "onStart:Đăng nhập thành công trước đó ")
            val nameUser = currentUser.displayName
            intentStartActivity.putExtra("user", nameUser)
            Log.d(javaClass.simpleName, "loginWithFireBase: $nameUser")
            Log.d(javaClass.simpleName, "loginWithFireBase: ${currentUser.email}")
            startActivityForResult.launch(intentStartActivity)
        } else {
            auth.signInWithEmailAndPassword(userName, pass)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(javaClass.simpleName, "signInWithEmail:thành công")
                        startActivityForResult.launch(intentStartActivity)
                        val user = auth.currentUser
                        intentStartActivity.putExtra("user", user?.displayName)
                        startActivityForResult.launch(intentStartActivity)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(javaClass.simpleName, "signInWithEmail:Lỗi")
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                }

        }

    }

}