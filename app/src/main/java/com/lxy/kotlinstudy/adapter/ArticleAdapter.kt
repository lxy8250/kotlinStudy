package com.lxy.kotlinstudy.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.net.modle.Datas

/**
 * Created by Administrator on 2018/4/4.
 */
class ArticleAdapter(private val items: List<Datas>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private lateinit var listener : OnIClickListener

    interface OnIClickListener{
        fun onClick(view : View,position : Int)
    }

    fun setOnIClickListener(listener: OnIClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        return ViewHolder(View.inflate(parent?.context, R.layout.home_item_article, null))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var itemData = items.get(position)
        holder!!.tvAuthor.text = itemData.author
        holder!!.tvTitle.text = itemData.title

        if (itemData.tags.isNotEmpty()){
            for (tag in itemData.tags) {
                holder!!.tvTag.text = "${tag.name} & ${holder!!.tvTag.text}"
            }
        }
        holder!!.tvTime.text = itemData.niceDate

        holder!!.ivLike.setOnClickListener {
            if (listener != null){
                listener.onClick(holder!!.ivLike,position)
            }
        }

        holder!!.cardView.setOnClickListener {
            if (listener != null){
                listener.onClick(holder!!.cardView,position)
            }
        }
    }



    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

        var cardView : CardView = itemView!!.findViewById(R.id.home_item_article_card)
        var tvHeader : ImageView = itemView!!.findViewById(R.id.home_item_article_header)
        var tvAuthor : TextView = itemView!!.findViewById(R.id.home_item_article_author)
        var tvTime : TextView = itemView!!.findViewById(R.id.home_item_article_time)
        var tvTitle : TextView = itemView!!.findViewById(R.id.home_item_article_title)
        var tvTag : TextView = itemView!!.findViewById(R.id.home_item_article_tag)
        var ivLike : ImageView = itemView!!.findViewById(R.id.home_item_article_like)

    }
}