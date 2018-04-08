package com.lxy.kotlinstudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lxy.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Administrator on 2018/4/5.
 */
class DetailActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail)

        var string = intent.getStringExtra("data")
        Log.i("data",string)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
    }
}