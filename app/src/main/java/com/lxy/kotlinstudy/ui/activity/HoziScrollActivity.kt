package com.lxy.kotlinstudy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.adapter.ArticleAdapter
import com.lxy.kotlinstudy.adapter.TagAdapter
import com.lxy.kotlinstudy.base.BaseActivity
import com.lxy.kotlinstudy.base.IBaseAdapter
import com.lxy.kotlinstudy.net.NetListener
import com.lxy.kotlinstudy.net.modle.ArticleModel
import com.lxy.kotlinstudy.net.modle.Datas
import com.lxy.kotlinstudy.net.modle.KnowledgeModel
import com.lxy.kotlinstudy.ui.fragment.SystemFrag
import com.lxy.kotlinstudy.utils.HttpManager
import com.lxy.kotlinstudy.utils.TotalUtils
import com.lxy.kotlinstudy.utils.UserManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hoziscroll.*
import kotlinx.android.synthetic.main.home_item_article.*

/**
 * Created by Administrator on 2018/4/24.
 */
class HoziScrollActivity : BaseActivity() {

    private lateinit var tagAdapter: TagAdapter
    private var page : Int = 0
    lateinit var data : KnowledgeModel.Data


    override fun initOption() {
        val string = intent.getStringExtra("knowString")
        data = Gson().fromJson(string,KnowledgeModel.Data::class.java)

        initView()
        initListener()
//        loadData(data.children[0].id)
    }

    override fun setToolbarTitle(): String = data.name

    override fun setLayoutId(): View {
        return View.inflate(this,R.layout.activity_hoziscroll,null)
    }


    private fun initView() {

        tag_recycle.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        tagAdapter = TagAdapter(this,data.children,R.layout.item_tag)
        tag_recycle.adapter = tagAdapter


    }

    private fun initListener() {

        tagAdapter.setOnIClickListener(object : IBaseAdapter.OnIClickListener{
            override fun onClick(view: View, position: Int) {
                var frag = supportFragmentManager.findFragmentById(R.id.hozi_frag) as SystemFrag
                frag.refreshUi(data.children[position].id)
            }
        })
    }


}