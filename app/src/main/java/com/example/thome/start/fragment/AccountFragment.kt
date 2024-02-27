package com.example.thousework.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thome.R
import com.example.thome.databinding.FragmentAccountBinding
import com.example.thome.start.InforUserActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class AccountFragment : Fragment() {
    lateinit var bindingFragmnetAccount: FragmentAccountBinding
    var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingFragmnetAccount = FragmentAccountBinding.inflate(layoutInflater, container, false)
        var emailName = auth.currentUser?.email
        bindingFragmnetAccount.tvName.text = emailName
        setHandlerEvent()
        return bindingFragmnetAccount.root
    }

    fun setHandlerEvent() {
        bindingFragmnetAccount.tvName.setOnClickListener {
            var intentDetail = Intent(context, InforUserActivity::class.java)
            startActivity(intentDetail)

        }
    }

}