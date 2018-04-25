package com.lxy.kotlinstudy.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Administrator on 2018/4/24.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())

    }

    abstract fun setLayoutId(): Int





}
