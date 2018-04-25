package com.lxy.kotlinstudy.adapter

import android.content.Context
import android.widget.TextView
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.base.IBaseAdapter
import com.lxy.kotlinstudy.base.ViewHolder
import com.lxy.kotlinstudy.net.modle.Children
import kotlinx.android.synthetic.main.item_tag.view.*

/**
 * Created by Administrator on 2018/4/25.
 */
class TagAdapter(
        context: Context,
        dataList: List<Children>,
        layoutId : Int) : IBaseAdapter<Children>(context, dataList, layoutId){

    override fun bindData(holder: ViewHolder?, t: Children) {
        holder!!.setText(R.id.item_tag_tv,t.name)
        holder!!.getView<TextView>(R.id.item_tag_tv).setOnClickListener {
            listener?.onClick(it,holder.pos)
        }
    }

}