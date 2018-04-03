package com.lxy.kotlinstudy.net.modle

/**
 * Created by Administrator on 2018/3/29.
 */
data class LoginModel (
        var data : Data,
        var errorCode : Int,
        var errorMsg : String){

    data class Data(
            var id: Int,
            var username: String,
            var password: String,
            var icon: String?,
            var type: Int,
            var email:String?,
            var collectIds: List<Int>?
    )
}