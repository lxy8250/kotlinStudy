package com.lxy.kotlinstudy.net

import com.lxy.kotlinstudy.net.modle.LoginModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Administrator on 2018/3/26.
 */
interface NetServer {


    @POST("user/login")
    fun login(@Query("username") username : String,
              @Query("password") password : String): Observable<LoginModel>

    @POST("user/register")
    fun register(@Query("username") username : String,
              @Query("password") password : String,
              @Query("repassword") repassword : String): Observable<LoginModel>


    @GET("navi/json")
    fun getNavi() : Call<ResponseBody>


}