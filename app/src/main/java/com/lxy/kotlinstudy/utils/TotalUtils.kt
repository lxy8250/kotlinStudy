package com.lxy.kotlinstudy.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.widget.ProgressBar
import android.widget.Toast
import com.lxy.kotlinstudy.R

/**
 * Created by Administrator on 2018/4/23.
 */
object TotalUtils {

    lateinit var bar : ProgressDialog

    /**
     * 弹 Toast,字符串类型
     */
    fun showToast(context: Context,str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
    /**
     * 弹 Toast,字符串资源id
     */
    fun showToast(context: Context, strResID: Int) {
        showToast(context,context.getString(strResID))
    }

    fun showDialog(context: Context,message : String,listener : DialogInterface.OnClickListener){
        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(message)
        builder.setNegativeButton(R.string.cancel, null)
        builder.setPositiveButton(R.string.sure,listener)
        builder.create().show()
    }

    fun showWaitDialog(context: Context,message : String,listener : DialogInterface.OnClickListener){
        bar = ProgressDialog(context)
        bar.setMessage(message)
        bar.show()
    }

    fun closeWaitDialog(){
        if (bar != null && bar.isShowing){
            bar.cancel()
        }
    }
}

