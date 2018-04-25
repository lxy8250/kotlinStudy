package com.lxy.kotlinstudy.utils

import android.util.Log
import com.google.gson.Gson
import com.lxy.kotlinstudy.net.NetListener
import com.lxy.kotlinstudy.net.modle.LoginModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Administrator on 2018/4/21.
 */
class UserManager {

    companion object {
        val manager by lazy(LazyThreadSafetyMode.NONE){
            UserManager()
        }
    }

    /**
     * 登录
     */
    fun login(username : String,password : String,listener : NetListener){
        HttpManager.manager.getServer()
                .login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseData : LoginModel ->
                    var gson = Gson()

                    listener.onSuccess(gson.toJson(responseData))

                }, {
                    error ->
                    listener.onFailed(error)
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }

    /**
     * 收藏站内文章
     */
    fun collect(username: String,id : Int,listener: NetListener){
        HttpManager.manager.getServer().collect(username,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseData : RequestBody ->

                    listener.onSuccess(responseData.toString())

                }, {
                    error ->
                    listener.onFailed(error)
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }

    /**
     * 收藏站外文章
     */
    fun collectOutSide(username: String,
                       title : String,anthor : String, link : String,
                       listener: NetListener){
        HttpManager.manager.getServer().collectOutSide(username,title,anthor,link)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseData : RequestBody ->

                    listener.onSuccess(responseData.toString())

                }, {
                    error ->
                    listener.onFailed(error)
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }

    fun collectOutSide(
                       title : String,anthor : String, link : String,
                       listener: NetListener){
        HttpManager.manager.getServer().collectOutSide(title,anthor,link)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseData : RequestBody ->

                    listener.onSuccess(responseData.toString())

                }, {
                    error ->
                    listener.onFailed(error)
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }
}