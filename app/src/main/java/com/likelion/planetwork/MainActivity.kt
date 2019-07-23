package com.likelion.planetwork

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://limitless-oasis-98552.herokuapp.com")
                //배포한 웹페이지의 BaseUrl
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //만들었던 인터페이스 기능 create
        var server = retrofit.create(PLNWService::class.java)

        //regiter 하기위해 값을 모두 입력하고 제출 버튼을 누를 때
        Submit.setOnClickListener {

            //라디오버튼으로 성별 체크
            when (gender.checkedRadioButtonId){
                R.id.Male ->
                    genderChecked.text = "male"
                R.id.Female ->
                    genderChecked.text = "female"
            }

            server.RegisterRequest(emailText.text.toString(),
                                    username.text.toString(),
                                    password.text.toString(),
                                    repassword.text.toString(),
                                    nameText.text.toString(),
                                    nickname.text.toString(),
                                    genderChecked.text.toString(),
                                    birthday.text.toString()).enqueue(object : Callback<Register>{
                //서버와 통신에 실패했을 때.
                override fun onFailure(call: Call<Register>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.","Error!")
                }
                override fun onResponse(call: Call<Register>, response: Response<Register>) {
                    println(response?.body().toString())
                    Log.e("서버와 통신 성공!","Success")
                }
            })
        }

        val intent = Intent(this, MessageActivity::class.java)
        startActivity(intent)
        finish()
    }
}
