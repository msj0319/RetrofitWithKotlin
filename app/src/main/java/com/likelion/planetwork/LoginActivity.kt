package com.likelion.planetwork

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.activity_message.Submit2
import kotlinx.android.synthetic.main.activity_message.username
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var signedin = 0
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        var activity = this
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://limitless-oasis-98552.herokuapp.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var server = retrofit.create(PLNWService::class.java)

        Submit2.setOnClickListener {
            var username = username2.text.toString()
            var password = Password.text.toString()

            SuccessLogin(this).Login(username, password)
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
        }

    }
}