package com.likelion.planetwork

import android.app.Service
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.activity_message.Submit2
import kotlinx.android.synthetic.main.activity_message.username
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //var activity  = this
        val retrofit = Retrofit.Builder()
            .baseUrl("https://limitless-oasis-98552.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var server = retrofit.create(PLNWService::class.java)

        Submit2.setOnClickListener {
            server.LoginRequest(username2.text.toString(),Password.text.toString())
                .enqueue(object : Callback<LoginForm>{
                    override fun onFailure(call: Call<LoginForm>, t: Throwable) {
                        Log.e("서버와 통신에 실패했습니다.", "Error!")
                        Toast.makeText(this@LoginActivity,"인터넷이 없습니다.",Toast.LENGTH_SHORT).show()
                   }

                    override fun onResponse(call: Call<LoginForm>, response: Response<LoginForm>) {
                        if (response?.code() == 200) {
                            Toast.makeText(this@LoginActivity,"로그인에 성공했습니다.",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                })
        }
    }
}