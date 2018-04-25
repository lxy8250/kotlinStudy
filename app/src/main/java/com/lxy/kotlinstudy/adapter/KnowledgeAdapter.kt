package com.lxy.kotlinstudy.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.R.id.item_knowledge_content
import com.lxy.kotlinstudy.base.IBaseAdapter
import com.lxy.kotlinstudy.base.ViewHolder
import com.lxy.kotlinstudy.net.modle.KnowledgeModel
import kotlinx.android.synthetic.main.item_knowledge.*

/**
 * Created by Administrator on 2018/4/24.
 */
class KnowledgeAdapter (
        context: Context,
        dataList: MutableList<KnowledgeModel.Data>,
        layoutId : Int) : IBaseAdapter<KnowledgeModel.Data>(context, dataList, layoutId) {

    override fun bindData(holder: ViewHolder?, t: KnowledgeModel.Data) {
        holder!!.setText(R.id.item_knowledge_title,t.name)
        var tv = holder.getView<TextView>(R.id.item_knowledge_content)
        var string = ""
        for (item in t.children) {
             string = "$string   ${item.name}"
        }
        tv.text = string

        holder!!.convertView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if(listener != null){
                    listener.onClick(p0!!,holder.pos)
                }
            }
        })
    }


}