package com.likelion.planetwork

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//회원가입을 위한 data class 작성
data class Register (
    var email:String,
    var username:String,
    var password1:String,
    var password2:String,
    var name:String,
    var nickname:String,
    var gender:String,
    var birth_date:String
)
interface PLNWService {
    @FormUrlEncoded
    @POST("/user/register/")
    //BaseUrl 뒤에 들어가는 register 기능 페이지
    fun RegisterRequest(@Field("email")email: String,
                        @Field("username")username: String,
                        @Field("password1")password1: String,
                        @Field("password2")password2: String,
                        @Field("name")name: String,
                        @Field("nickname")nickname: String,
                        @Field("gender")gender: String,
                        @Field("birth_date")birth_date: String):Call<Register>
}