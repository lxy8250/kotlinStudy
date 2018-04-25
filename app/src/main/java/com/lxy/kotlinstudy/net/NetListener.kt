package com.lxy.kotlinstudy.net

import org.json.JSONObject

/**
 * Created by Administrator on 2018/4/21.
 */
interface NetListener {
    fun onSuccess(json : String)
    fun onFailed(e : Throwable)
}