package com.lxy.kotlinstudy.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lxy.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Created by Administrator on 2018/4/24.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        root_layout.addView(setLayoutId())

        initOption()
        initToolbar()

    }

    abstract fun initOption()

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = setToolbarTitle()
        toolbar.setNavigationOnClickListener{
            finish()
        }
    }

    abstract fun setToolbarTitle() : String

    abstract fun setLayoutId(): View


}
