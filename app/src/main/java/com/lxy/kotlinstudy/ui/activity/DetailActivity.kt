package com.lxy.kotlinstudy.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.lxy.kotlinstudy.R.id.detail_love
import com.lxy.kotlinstudy.R.id.detail_share
import com.lxy.kotlinstudy.base.BaseActivity
import com.lxy.kotlinstudy.net.NetListener
import com.lxy.kotlinstudy.net.modle.Datas
import com.lxy.kotlinstudy.utils.UserManager
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Administrator on 2018/4/5.
 */
class DetailActivity : BaseActivity() {

    lateinit var webData : Datas
    lateinit var webSet : WebSettings

    override fun initOption() {
        var string = intent.getStringExtra("data")
        Log.i("data",string)
        webData = Gson().fromJson(string,Datas::class.java)
        initWeb()
        initListener()
    }


    override fun setToolbarTitle(): String = webData.title

    override fun setLayoutId(): View {
        return View.inflate(this,R.layout.activity_detail,null)
    }



    private fun initWeb() {
        detail_web.loadUrl(webData.link)
        webSet = detail_web.settings
        webSet.javaScriptEnabled = true

    }

    private fun initListener(){
        toolbar.setOnMenuItemClickListener (object : Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {

                when(item?.itemId){
                    R.id.detail_love ->{
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
                    R.id.detail_share -> {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        val string = "${webData.title} ${webData.link}"
                        intent.putExtra(Intent.EXTRA_TEXT, string)
                        startActivity(Intent.createChooser(intent, "分享"))

                        return true
                    }
                    else -> {
                        return true
                    }
                }

            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.detail_love ->{
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
            R.id.detail_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                val string = "wanandroid 分享[${webData.title}] ${webData.link}"
                intent.putExtra(Intent.EXTRA_TEXT, string)
                startActivity(Intent.createChooser(intent, getString(R.string.share)))

                return true
            }
            R.id.detail_other -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.action = "android.intent.action.VIEW"
                val uri = Uri.parse("${webData.link}")
                intent.data = uri
                startActivity(Intent.createChooser(intent, getString(R.string.other)))
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbar.inflateMenu(R.menu.detail_menu )
        return super.onCreateOptionsMenu(menu)
    }
}