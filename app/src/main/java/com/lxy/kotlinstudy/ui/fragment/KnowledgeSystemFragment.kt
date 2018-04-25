package com.lxy.kotlinstudy.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lxy.kotlinstudy.R
import com.lxy.kotlinstudy.adapter.ArticleAdapter
import com.lxy.kotlinstudy.adapter.KnowledgeAdapter
import com.lxy.kotlinstudy.base.IBaseAdapter
import com.lxy.kotlinstudy.net.modle.KnowledgeModel
import com.lxy.kotlinstudy.ui.activity.HoziScrollActivity
import com.lxy.kotlinstudy.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_knowledge.*

/**
 * Created by Administrator on 2018/4/24.
 */
class KnowledgeSystemFragment : Fragment() {

    lateinit var dataList: MutableList<KnowledgeModel.Data>
    lateinit var adapter : KnowledgeAdapter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_knowledge, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        loadData()
    }

    private fun initView() {
        dataList = mutableListOf<KnowledgeModel.Data>()
        knowledge_recycle.layoutManager = LinearLayoutManager(context)
        adapter = KnowledgeAdapter(context,dataList,R.layout.item_knowledge)
        knowledge_recycle.adapter = adapter


    }

    @SuppressLint("ResourceAsColor")
    private fun initListener(){
        adapter.setOnIClickListener(object : IBaseAdapter.OnIClickListener{
            override fun onClick(view: View, position: Int) {
                var intent = Intent(context,HoziScrollActivity::class.java)
                intent.putExtra("","")
                startActivity(intent)
            }
        })

        knowledge_refresh.setColorSchemeColors(R.color.colorPrimary)
        knowledge_refresh.setOnRefreshListener {

            loadData()
        }
    }

    private fun loadData() {
        knowledge_refresh.isRefreshing = true
        HttpManager.manager.getServer().getKnowledge()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.errorCode == 0) {
                        dataList.clear()
                        dataList.addAll(it!!.data)
                        adapter.notifyDataSetChanged()
                        knowledge_refresh.isRefreshing = false
                    }
                },{
                    it.printStackTrace()
                })
    }
}