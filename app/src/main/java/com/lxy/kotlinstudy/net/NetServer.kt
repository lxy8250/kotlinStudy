package com.lxy.kotlinstudy.net

import com.lxy.kotlinstudy.net.modle.ArticleModel
import com.lxy.kotlinstudy.net.modle.FriendModel
import com.lxy.kotlinstudy.net.modle.KnowledgeModel
import com.lxy.kotlinstudy.net.modle.LoginModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Administrator on 2018/3/26.
 */
interface NetServer {


    /**
     * 登录
     * http://www.wanandroid.com/user/login
     */
    @POST("user/login")
    fun login(@Query("username") username : String,
              @Query("password") password : String): Observable<LoginModel>

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     */
    @POST("user/register")
    fun register(@Query("username") username : String,
              @Query("password") password : String,
              @Query("repassword") repassword : String): Observable<LoginModel>


    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     */
    @GET("article/list/{page}/json")
    fun homeArticle(@Path("page") page : Int) : Observable<ArticleModel>

    /**
     * http://www.wanandroid.com/friend/json
     * 常用网站
     */
    @GET("friend/json")
    fun getFriendList() : Observable<FriendModel>

    /**
     * http://www.wanandroid.com/tree/json
     * 知识体系
     */
    @GET("tree/json")
    fun getKnowledge() : Observable<KnowledgeModel>

    /**
     * http://www.wanandroid.com/article/list/0/json?cid=60
     * 知识体系下的文章
     */
    @GET("article/list/{page}/json?cid=60")
    fun getKnowledgeArticle(
            @Path("page") page: Int,
            @Query("cid") cid : Int
    ) : Observable<ArticleModel>

    /**
     * http://www.wanandroid.com/project/tree/json
     * 项目分类
     */
    @GET("project/tree/json")
    fun getProject() : Observable<KnowledgeModel>

    /**
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * 项目列表数据
     */
    @GET("project/list/{page}/json")
    fun getProjectList(
            @Path("page") page: Int,
            @Query("cid") cid: Int
    ) : Observable<ArticleModel>

    @POST("{username}/collect/{id}/json")
    fun collect(@Path("username") username: String,
                @Path("id") id : Int) : Observable<RequestBody>

    @POST("{username}/collect/add/json")
    @FormUrlEncoded
    fun collectOutSide(
            @Query("username") username: String,
            @Field("title") title : String,
            @Field("author") author : String,
            @Field("link") link : String
    ):Observable<RequestBody>

    /**
     * 收藏站外网站
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    fun collectOutSide(
            @Field("title") title : String,
            @Field("author") author : String,
            @Field("link") link : String
    ):Observable<RequestBody>
}