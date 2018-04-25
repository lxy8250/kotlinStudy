package com.lxy.kotlinstudy.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.net.NetListener
import com.lxy.kotlinstudy.net.modle.Datas
import com.lxy.kotlinstudy.utils.UserManager
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Administrator on 2018/4/5.
 */
class DetailActivity : AppCompatActivity() {

    lateinit var webData : Datas
    lateinit var webSet : WebSettings

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        initWeb()

    }

    private fun initWeb() {
        detail_web.loadUrl(webData.link)
        webSet = detail_web.settings
        webSet.javaScriptEnabled = true

    }

    private fun initToolbar() {
        var string = intent.getStringExtra("data")
        Log.i("data",string)
        webData = Gson().fromJson(string,Datas::class.java)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = webData.title
        toolbar.setNavigationOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                finish()
            }
        })

        toolbar.setOnMenuItemClickListener (object : Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                var username = getSharedPreferences(getString(R.string.sp_user), Context.MODE_PRIVATE)
                        .getString("username","")
                UserManager.manager.collectOutSide(webData.title,webData.author,webData.link,
                        object : NetListener {
                    override fun onFailed(e: Throwable) {
                        Log.i("tag",e.message)
                    }

                    override fun onSuccess(json: String) {
                        ToastUtils.showShort("收藏成功")
                    }
                })

                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var view = toolbar.inflateMenu(R.menu.detail_menu )
        return super.onCreateOptionsMenu(menu)
    }
}