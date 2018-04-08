package com.lxy.kotlinstudy.net.modle

/**
 * Created by Administrator on 2018/4/3.
 */
data class ArticleModel(
        val data: Data,
        val errorCode: Int,
        val errorMsg: String
)

data class Data(
        val curPage: Int,
        val datas: List<Datas>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)

data class Datas(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>,
        val title: String,
        val type: Int,
        val visible: Int,
        val zan: Int
)

data class Tag(
        val name: String,
        val url: String
)