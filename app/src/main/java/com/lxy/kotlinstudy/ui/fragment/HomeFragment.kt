package com.lxy.kotlinstudy.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.lxy.kotlinstudy.adapter.ArticleAdapter

import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.net.NetListener
import com.lxy.kotlinstudy.net.modle.ArticleModel
import com.lxy.kotlinstudy.net.modle.Datas
import com.lxy.kotlinstudy.ui.activity.DetailActivity
import com.lxy.kotlinstudy.utils.HttpManager
import com.lxy.kotlinstudy.utils.TotalUtils
import com.lxy.kotlinstudy.utils.UserManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_knowledge.*
import kotlinx.android.synthetic.main.home_item_article.*

class HomeFragment : Fragment() {

    var page = 0
    var LOAD_MORE_POSITION = 5
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
        loadData()
        initListener()

    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        var manager = home_recycle.layoutManager as LinearLayoutManager
        // 得到可显示的最后一个item位置
        var position = manager.findFirstVisibleItemPosition()
        var total = adapter.itemCount
        if (total <= (position + LOAD_MORE_POSITION)){
            loadData()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initListener() {
        adapter.setOnIClickListener(object : ArticleAdapter.OnIClickListener{
            override fun onClick(view: View, position: Int) {
                when(view.id){
                    R.id.home_item_article_card -> {
                        var intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("data",Gson().toJson(dataList[position]))
                        context.startActivity(intent)
                    }
                    R.id.home_item_article_like -> {
                        if (dataList[position].zan == 0) {
                            home_item_article_like.setImageResource(R.drawable.loveblue)
                            var username = context.getSharedPreferences(getString(R.string.sp_user), Context.MODE_PRIVATE)
                                    .getString("username","")
                            UserManager.manager.collect(username,dataList[position].id,object :NetListener{
                                override fun onFailed(e: Throwable) {
                                    Log.i("tag",e.message)
                                }

                                override fun onSuccess(json: String) {
                                    TotalUtils.showToast(context,"收藏成功")

                                }
                            })
                        }else{
                            home_item_article_like.setImageResource(R.drawable.love)
                        }
                    }
                }
            }
        })

        home_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                loadMore()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {

            }
        })

        home_refresh.setColorSchemeColors(R.color.colorPrimary)
        home_refresh.setOnRefreshListener {
            loadData()
        }
    }

    /***
     * 加载首页文章
     */
    private fun loadData() {
        home_refresh.isRefreshing = true
        HttpManager.manager.getServer().homeArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    article: ArticleModel ->
                    home_refresh.isRefreshing = false
                    if (article.errorCode === 0){
                        dataList.addAll(article.data.datas)
                        adapter.notifyDataSetChanged()
                        ++page
                    }
                }, {

                    error ->
                    error.printStackTrace()
                    home_refresh.isRefreshing = false
                }, {

                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }



}
