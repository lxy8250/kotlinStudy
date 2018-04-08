package com.lxy.kotlinstudy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.lxy.kotlinstudy.net.modle.LoginModel
import com.lxy.kotlinstudy.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() ,View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()


    }

    private fun initListener(){
        login_btn_login.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                var username : String = login_et_username.text.toString()
                var password : String = login_et_password.text.toString()
                HttpManager.manager.getServer().login(username,password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            responseData : LoginModel ->
                            Log.i("net",responseData.errorCode.toString())
                            var intent = Intent()
                            intent.setClass(this@LoginActivity,MainActivity::class.java)
                            startActivity(intent)

                        }, {
                            error ->
                            error.printStackTrace()
                        }, {
                            Log.i("Kotlin", "onComplete")
                        }, {
                            Log.i("Kotlin", "onStart")
                        })
            }

        })
    }


    override fun onClick(v: View?) {

    }
}



