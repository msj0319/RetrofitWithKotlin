package com.likelion.planetwork

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuccessLogin(activity : Activity) {

    var activity = activity
    val retrofit = Retrofit.Builder()
        .baseUrl("https://limitless-oasis-98552.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(PLNWService::class.java)

    //로그인 기능
    fun Login(ID: String, PW: String) {
        server.LoginRequest(ID, PW)
            .enqueue(object : Callback<LoginForm> {
                override fun onFailure(call: Call<LoginForm>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.", "Error!")
                    //Toast.makeText(LoginActivity, "인터넷이 없습니다.", Toast.LENGTH_SHORT).show()
                    activity.toast("인터넷이 없습니다! 인터넷을 연결 해주세요")
                }

                override fun onResponse(call: Call<LoginForm>, response: Response<LoginForm>) {
                    if (response?.code() == 200) {
                        //Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        activity.toast("로그인에 성공했습니다")
                        signedin = response?.body()?.user?.id!!.toInt()
                        activity.finish()
                    }
                }
            })
    }
    
    //회원 pk 가져오기
    fun getUserID(id: Int) {
        server.UserInfo(signedin).enqueue(object : Callback<Register> {
            override fun onFailure(call: Call<Register>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<Register>, response: Response<Register>) {
//                if (response.code() == 200) {
//                   //activity.HelloUser.text = "$signedin" + "내 이름 : " + response?.body()?.username!!.toString()
//                }
            }
        })
    }
}
