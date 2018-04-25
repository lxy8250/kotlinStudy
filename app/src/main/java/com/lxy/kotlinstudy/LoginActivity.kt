package com.lxy.kotlinstudy

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.lxy.kotlinstudy.net.NetListener
import com.lxy.kotlinstudy.net.modle.LoginModel
import com.lxy.kotlinstudy.utils.UserManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()


    }

    private fun initListener(){
        login_btn_login.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                var username: String = login_et_username.text.toString()
                var password: String = login_et_password.text.toString()


                UserManager.manager.login(username, password, object : NetListener {
                    override fun onSuccess(json: String) {
                        val responseData = Gson().fromJson(json, LoginModel::class.java)
                        if (responseData.errorCode == 0) {
                            var editor = getSharedPreferences(getString(R.string.sp_user), Context.MODE_PRIVATE).edit()
                            editor.putString("username", responseData.data.username)
                            editor.putString("password", responseData.data.password)
                            editor.putString("email", responseData.data.email)
                            editor.putInt("type", responseData.data.type)
                            editor.apply()

                            Log.i("net", responseData.errorCode.toString())
                            var intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }else {
                            Toast.makeText(this@LoginActivity,responseData.errorMsg, Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailed(e: Throwable) {
                        e.printStackTrace()
                    }


                })

            }
        })
    }



}



