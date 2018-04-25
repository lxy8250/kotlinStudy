package com.lxy.kotlinstudy.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.adapter.ArticleAdapter
import com.lxy.kotlinstudy.net.modle.ArticleModel
import com.lxy.kotlinstudy.net.modle.Datas
import com.lxy.kotlinstudy.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hoziscroll.*

/**
 * Created by Administrator on 2018/4/24.
 */
class HoziScrollActivity : AppCompatActivity() {

    lateinit var adapter: ArticleAdapter
    private var dataList = mutableListOf<Datas>()
    var page : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoziscroll)

        horizon_recycle.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(dataList)
        horizon_recycle.adapter = adapter
        HttpManager.manager.getServer()
                .getKnowledgeArticle(page,60)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.errorCode == 0){
                        dataList.clear()
                        dataList.addAll(it.data.datas)
                        adapter.notifyDataSetChanged()
                        ++page

                    }
                })
    }


}