package com.lxy.kotlinstudy.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Administrator on 2018/4/5.
 */
class ViewHolder : RecyclerView.ViewHolder {

     var views : SparseArray<View>
     var convertView : View
     var context : Context
     var pos : Int = 0

     constructor( context :Context, convertView : View, parent : ViewGroup) : super(convertView) {
         views = SparseArray()
         this.convertView = convertView
         this.context = context
    }

    companion object {
         fun get(context: Context,parent: ViewGroup,layoutId : Int) : ViewHolder{
            var itemView = LayoutInflater.from(context).inflate(layoutId,parent,false)
            var holder = ViewHolder(context,itemView,parent)
            return holder
        }
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */

    public fun <T : View>  getView( viewId : Int) : T{
        var view = views.get(viewId)
        if (view == null){
            view = convertView.findViewById(viewId)
            views.append(viewId,view)
        }
        return view as T
    }

    fun updatePosition(position : Int){
        this.pos = position
    }

}