package com.lxy.kotlinstudy.utils

import com.lxy.kotlinstudy.base.Constant
import com.lxy.kotlinstudy.net.NetServer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Administrator on 2018/3/26.
 */
class HttpManager {

    companion object {
        val manager by lazy(LazyThreadSafetyMode.NONE){
            HttpManager()
        }
    }



    fun  getServer(): NetServer {
        var retrofit = Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var server = retrofit.create(NetServer::class.java)
        return server
    }

    fun  getServer(baseurl : String): NetServer {
        var retrofit = Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var server = retrofit.create(NetServer::class.java)
        return server
    }

}