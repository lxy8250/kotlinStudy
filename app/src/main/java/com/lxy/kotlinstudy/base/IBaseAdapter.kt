package com.lxy.kotlinstudy.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Administrator on 2018/4/5.
 */
abstract class IBaseAdapter<T>( val context : Context?,
                       val dataList : List<T>?,
                       val layoutId : Int?) : RecyclerView.Adapter<ViewHolder>() {


    protected lateinit var listener : OnIClickListener

    interface OnIClickListener{
        fun onClick(view : View, position : Int)
    }

    fun setOnIClickListener(listener: OnIClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var viewHolder = ViewHolder.get(context!!, parent!!, layoutId!!)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return dataList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.updatePosition(position)
        bindData(holder, dataList!![position] )
    }

    abstract fun bindData(holder: ViewHolder?,t : T)

}