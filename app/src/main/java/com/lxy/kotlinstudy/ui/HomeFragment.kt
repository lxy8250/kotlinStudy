package com.lxy.kotlinstudy.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lxy.kotlinstudy.adapter.ArticleAdapter

import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.R.id.home_item_article_like
import com.lxy.kotlinstudy.R.layout.home_item_article
import com.lxy.kotlinstudy.net.modle.ArticleModel
import com.lxy.kotlinstudy.net.modle.Datas
import com.lxy.kotlinstudy.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_item_article.*

class HomeFragment : Fragment() {

    var page = 0
    lateinit var dataList : MutableList<Datas>
    lateinit var adapter :ArticleAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                Log.i("Kotlin", "onComplete")
                HttpManager.manager.getServer().homeArticle(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            acticle: ArticleModel ->
                            Log.i("acticle",acticle.data.size.toString())
                        }, {
                            error ->
                            error.printStackTrace()
                        }, {
                            Log.i("Kotlin", "onComplete")
                        }, {
                            Log.i("Kotlin", "onStart")
                        })
            }

        })
        dataList = mutableListOf<Datas>()
        adapter = ArticleAdapter(dataList)
        home_recycle.layoutManager = LinearLayoutManager(context)
        home_recycle.adapter = adapter
        loadData(adapter)
        initListener()


    }

    private fun initListener() {
        adapter.setOnIClickListener(object : ArticleAdapter.OnIClickListener{
            override fun onClick(view: View, position: Int) {
                when(view.id){
                    R.id.home_item_article_card -> {
                        var intent = Intent(context,DetailActivity::class.java)
                        intent.putExtra("data",dataList[position].chapterId)
                        startActivity(intent)
                    }
                    R.id.home_item_article_like -> {
                        if (dataList[position].zan == 0) {
                            home_item_article_like.setImageResource(R.drawable.loveblue)
                        }else{
                            home_item_article_like.setImageResource(R.drawable.love)
                        }
                    }
                }
            }
        })
    }

    private fun loadData(adapter: ArticleAdapter) {
        HttpManager.manager.getServer().homeArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    article: ArticleModel ->
                    if (article.errorCode === 0){
                        dataList.addAll(article.data.datas)
                        adapter.notifyDataSetChanged()
                    }
                }, {
                    error ->
                    error.printStackTrace()
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }


}
